package learn.rockClimbing.data;

import learn.rockClimbing.data.mappers.GymMapper;
import learn.rockClimbing.data.mappers.RouteGradeMapper;
import learn.rockClimbing.data.mappers.RouteMapper;
import learn.rockClimbing.models.Gym;
import learn.rockClimbing.models.Route;
import learn.rockClimbing.models.RouteGrade;
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
    public List<Route> findAllRoutes() {
        final String sql = "select route_id, gym_id, route_type, attempts, set_date " +
                "from route;";

        List<Route> routes = jdbcTemplate.query(sql, new RouteMapper());

        for (Route route : routes) {
            addGym(route);
            addRouteGrade(route);
        };

        return routes;
    }

    @Override
    public Route findRouteById(int routeId) {
        final String sql = "select route_id, gym_id, route_type, attempts, set_date " +
                "from route " +
                "where route_id = ?;";

        Route route = jdbcTemplate.query(sql, new RouteMapper(), routeId).stream().findFirst().orElse(null);

        if (route != null) {
            addGym(route);
            addRouteGrade(route);
        }

        return route;
    }

    @Override
    public List<Route> findRoutesByGym(int gymId) {
        final String sql = "select r.route_id, r.gym_id, rg.grade_level, r.route_type, r.attempts, r.set_date " +
                "from route r " +
                "inner join route_grade rg on r.route_grade_id = rg.route_grade_id " +
                "where r.gym_id = ?;";

        List<Route> routes = jdbcTemplate.query(sql, new RouteMapper(), gymId);

        for (Route route : routes) {
            addGym(route);
            addRouteGrade(route);
        };

        return routes;
    }

    @Override
    public List<Route> findRoutesByClimber(int climberId) {
        final String sql = "select r.route_id, r.gym_id, rg.grade_level, r.route_type, r.attempts, r.set_date " +
                "from route r " +
                "inner join route_grade rg on r.route_grade_id = rg.route_grade_id " +
                "inner join climber_route cr on cr.route_id = r.route_id " +
                "inner join climber c on c.climber_id = cr.climber_id " +
                "where c.climber_id = ?;";

        List<Route> routes = jdbcTemplate.query(sql, new RouteMapper(), climberId);

        for (Route route : routes) {
            addGym(route);
            addRouteGrade(route);
        };

        return routes;
    }

    @Override
    public List<Route> findRoutesByGymAndClimber(int gymId, int climberId) {
        final String sql = "select r.route_id, r.gym_id, rg.grade_level, r.route_type, r.attempts, r.set_date " +
                "from route r " +
                "inner join route_grade rg on r.route_grade_id = rg.route_grade_id " +
                "inner join climber_route cr on cr.route_id = r.route_id " +
                "inner join climber c on c.climber_id = cr.climber_id " +
                "where c.climber_id = ? " +
                "and r.gym_id = ?;";

        List<Route> routes = jdbcTemplate.query(sql, new RouteMapper(), gymId, climberId);

        for (Route route : routes) {
            addGym(route);
            addRouteGrade(route);
        };

        return routes;
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
            ps.setString(3, route.getRouteType());
            ps.setInt(4, route.getAttempts());
            if (route.getSetDate() != null) {
                ps.setDate(5, Date.valueOf(route.getSetDate()));
            }
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
                route.getRouteType(),
                route.getAttempts(),
                route.getSetDate(),
                route.getRouteId()) > 0;
    }

    @Override
    public boolean deleteRouteById(int routeId) {
        final String deleteRouteSql = "delete from route where route_Id = ?;";
        return jdbcTemplate.update(deleteRouteSql, routeId) > 0;
    }

    private void addGym(Route route) {
        final String sql = "select g.gym_id, g.gym_name, g.city, g.state " +
                "from gym g " +
                "inner join route r on g.gym_id = r.gym_id " +
                "where r.route_id = ?;";
        Gym gym = jdbcTemplate.query(sql, new GymMapper(), route.getRouteId()).stream().findFirst().orElse(null);
        route.setGym(gym);
    }

    private void addRouteGrade(Route route) {
        final String sql = "select rg.route_grade_id, rg.grading_system, rg.grade_level " +
                "from route_grade rg " +
                "inner join route r on rg.route_grade_id = r.route_grade_id " +
                "where r.route_id = ?;";
        RouteGrade routeGrade = jdbcTemplate.query(sql, new RouteGradeMapper(), route.getRouteId()).stream().findFirst().orElse(null);
        route.setRouteGrade(routeGrade);
    }
}
