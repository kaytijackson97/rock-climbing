package learn.rockClimbing.data;

import learn.rockClimbing.models.ClimberRoute;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ClimberRouteJdbcTemplateRepository implements ClimberRouteRepository {

    private final JdbcTemplate jdbcTemplate;

    public ClimberRouteJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean add(ClimberRoute climberRoute) {
        final String sql = "insert into climber_route (climber_id, route_id) values (?, ?);";
        return jdbcTemplate.update(sql, climberRoute.getClimber().getClimberId(), climberRoute.getRoute().getRouteId()) > 0;
    };

    @Override
    public boolean deleteByKey(int climberId, int routeId) {
        final String sql = "delete from climber_route where climber_id = ? and route_id = ?;";
        return jdbcTemplate.update(sql, climberId, routeId) > 0;
    };
}
