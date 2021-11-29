package learn.rockClimbing.data;

import learn.rockClimbing.data.mappers.RouteMapper;
import learn.rockClimbing.models.Route;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class RouteJdbcTemplateRepository implements RouteRepository {
    private final JdbcTemplate jdbcTemplate;

    public RouteJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Route> findAll() {
        final String sql = "select r.route_id, r.gym_id, rg.grade_level, r.route_type, r.attempts, r.set_date " +
                "from route r " +
                "inner join route_grade rg on r.route_grade_id = rg.route_grade_id;";
        return jdbcTemplate.query(sql, new RouteMapper());
    }

    @Override
    public Route findRouteById(int routeId) {
        final String sql = "select r.route_id, r.gym_id, rg.grade_level, r.route_type, r.attempts, r.set_date " +
                "from route r " +
                "inner join route_grade rg on r.route_grade_id = rg.route_grade_id " +
                "where r.route_id = ?;";
        return jdbcTemplate.query(sql, new RouteMapper(), routeId).stream().findFirst().orElse(null);
    }

    @Override
    public List<Route> findRoutesByGym(int gymId) {
        final String sql = "select r.route_id, r.gym_id, rg.grade_level, r.route_type, r.attempts, r.set_date " +
                "from route r " +
                "inner join route_grade rg on r.route_grade_id = rg.route_grade_id " +
                "where r.gym_id = ?;";
        return jdbcTemplate.query(sql, new RouteMapper(), gymId);
    }

    @Override
    public List<Route> findRoutesByClimber(int climberId) {
        final String sql = "select r.route_id, r.gym_id, rg.grade_level, r.route_type, r.attempts, r.set_date " +
                "from route r " +
                "inner join route_grade rg on r.route_grade_id = rg.route_grade_id " +
                "inner join climber_gym cg on cg.gym_id = r.gym_id " +
                "inner join climber c on c.climber_id = cg.climber_id " +
                "where c.climber_id = ?;";
        return jdbcTemplate.query(sql, new RouteMapper(), climberId);
    }

    @Override
    public List<Route> findRoutesByGymAndClimber(int gymId, int climberId) {
        final String sql = "select r.route_id, r.gym_id, rg.grade_level, r.route_type, r.attempts, r.set_date " +
                "from route r " +
                "inner join route_grade rg on r.route_grade_id = rg.route_grade_id " +
                "inner join climber_gym cg on cg.gym_id = r.gym_id " +
                "inner join climber c on c.climber_id = cg.climber_id " +
                "where c.climber_id = ? " +
                "and r.gym_id = ?;";
        return jdbcTemplate.query(sql, new RouteMapper(), climberId, gymId);
    }

    @Override
    public Route addRoute(Route route) {
        if (route == null) {
            return null;
        }

        final String sql = "insert into route (gym_id, route_grade_id, route_type, attempts, set_date) " +
                "values (?,?,?,?,?);";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, route.getGym().getGymId());
            ps.setInt(2, route.getRouteGrade().getRouteGradeId());
            ps.setString(3, route.getRouteType().toString());
            ps.setInt(4, route.getAttempts());
            ps.setDate(5, Date.valueOf(route.getSetDate()));
            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        route.setRouteId(keyHolder.getKey().intValue());
        return route;
    }

    @Override
    public boolean editRoute(Route route) {
        if (route == null) {
            return false;
        }

        final String sql = "update route set " +
                "gym_id = ?, " +
                "route_grade_id = ?, " +
                "route_type = ?, " +
                "attempts = ?, " +
                "set_date = ? " +
                "where route_id = ?;";

        return jdbcTemplate.update(sql,
                route.getGym().getGymId(),
                route.getRouteGrade().getRouteGradeId(),
                route.getRouteType().toString(),
                route.getAttempts(),
                route.getSetDate(),
                route.getRouteId()) > 0;
    }

    @Override
    public boolean deleteRouteById(int routeId) {
        final String deleteRouteSql = "delete from route where route_Id = ?;";
        return jdbcTemplate.update(deleteRouteSql, routeId) > 0;
    }
}
