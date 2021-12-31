package learn.rockClimbing.data;

import learn.rockClimbing.data.mappers.ClimberMapper;
import learn.rockClimbing.data.mappers.GymMapper;
import learn.rockClimbing.data.mappers.RouteGradeMapper;
import learn.rockClimbing.data.mappers.RouteMapper;
import learn.rockClimbing.models.Climber;
import learn.rockClimbing.models.Gym;
import learn.rockClimbing.models.Route;
import learn.rockClimbing.models.RouteGrade;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ClimberJdbcTemplateRepository implements ClimberRepository {
    private final JdbcTemplate jdbcTemplate;

    public ClimberJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Climber> findAllClimbers() {
        final String sql = "select climber_id, climber_name, climber_age, length_of_time_climbing from climber;";
        List<Climber> climbers = jdbcTemplate.query(sql, new ClimberMapper());

        for (Climber climber : climbers) {
            addGyms(climber);
            addRoutes(climber);
        }

        return climbers;
    }

    @Override
    public Climber findByClimberId(int climberId) {
        final String sql = "select climber_id, climber_name, climber_age, length_of_time_climbing from climber " +
                "where climber_id = ?;";
        Climber climber = jdbcTemplate.query(sql, new ClimberMapper(), climberId).stream().findFirst().orElse(null);

        if(climber != null) {
            addGyms(climber);
            addRoutes(climber);
        }

        return climber;
    }

    @Override
    public Climber addClimber(Climber climber) {
        if (climber == null ||
            climber.getName() == null) {
            return null;
        }

        final String sql = "insert into climber (climber_name, climber_age, length_of_time_climbing) " +
                "values (?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, climber.getName());
            ps.setInt(2, climber.getAge());
            ps.setInt(3, climber.getMonthsClimbing());
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        climber.setClimberId(keyHolder.getKey().intValue());
        return climber;
    }

    @Override
    public boolean editClimber(Climber climber) {
        if (climber == null ||
            climber.getName() == null) {
            return false;
        }

        final String sql = "update climber set " +
                "climber_name = ?, " +
                "climber_age = ?, " +
                "length_of_time_climbing = ? " +
                "where climber_id = ?;";

        return jdbcTemplate.update(sql,
                climber.getName(),
                climber.getAge(),
                climber.getMonthsClimbing(),
                climber.getClimberId()) > 0;
    }

    @Override
    @Transactional
    public boolean deleteClimberById(int climberId) {
        jdbcTemplate.update("set sql_safe_updates = 0;");

        final String deleteClimberRouteSql = "delete from climber_route where climber_id = ?;";
        jdbcTemplate.update(deleteClimberRouteSql, climberId);

        jdbcTemplate.update("set sql_safe_updates = 1;");

        final String sql = "delete from climber where climber_id = ?;";
        return jdbcTemplate.update(sql, climberId) > 0;
    }

    private void addGyms(Climber climber) {
        final String sql = "select distinct g.gym_id, g.gym_name, g.city, g.state " +
                "from gym g " +
                "inner join route r on g.gym_id = r.gym_id " +
                "inner join climber_route cr on r.route_id = cr.route_id " +
                "where cr.climber_id = ?;";
        List<Gym> gyms = jdbcTemplate.query(sql, new GymMapper(), climber.getClimberId());
        climber.setGyms(gyms);
    }

    private void addRoutes(Climber climber) {
        final String climberSql = "select r.route_id, r.gym_id, r.route_grade_id, r.route_type, r.attempts, r.set_date " +
                "from route r " +
                "inner join climber_route cr on r.route_id = cr.route_id " +
                "where cr.climber_id = ?;";
        List<Route> routes = jdbcTemplate.query(climberSql, new RouteMapper(), climber.getClimberId());

        for (Route route : routes) {
            //set gym in route
            final String routeSql = "select g.gym_id, g.gym_name, g.city, g.state " +
                    "from gym g " +
                    "inner join route r on g.gym_id = r.gym_id " +
                    "where r.route_id = ?;";

            Gym gym = jdbcTemplate.query(
                    routeSql,
                    new GymMapper(),
                    route.getRouteId()
            ).stream().findFirst().orElse(null);

            route.setGym(gym);

            //set grade in route
            final String routeGradeSql = "select rg.route_grade_id, rg.grading_system, rg.grade_level " +
                    "from route_grade rg " +
                    "inner join route r on rg.route_grade_id = r.route_grade_id " +
                    "where r.route_id = ?;";
            RouteGrade routeGrade = jdbcTemplate.query(
                    routeGradeSql,
                    new RouteGradeMapper(),
                    route.getRouteId()
            ).stream().findFirst().orElse(null);
            route.setRouteGrade(routeGrade);
        }
        climber.setClimbs(routes);
    }
}
