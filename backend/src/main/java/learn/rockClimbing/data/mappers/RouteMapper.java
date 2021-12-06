package learn.rockClimbing.data.mappers;

import learn.rockClimbing.models.Route;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RouteMapper implements RowMapper<Route> {
    @Override
    public Route mapRow(ResultSet resultSet, int i) throws SQLException {
        Route route = new Route();

        route.setRouteId(resultSet.getInt("route_id"));
        String routeType = resultSet.getString("route_type");
        route.setRouteType(routeType);
        route.setAttempts(resultSet.getInt("attempts"));
        if (resultSet.getDate("set_date") != null) {
            route.setSetDate(resultSet.getDate("set_date").toLocalDate());
        }

        return route;
    }
}
