package learn.rockClimbing.data.mappers;

import learn.rockClimbing.models.Route;
import learn.rockClimbing.models.RouteType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RouteMapper implements RowMapper<Route> {
    @Override
    public Route mapRow(ResultSet resultSet, int i) throws SQLException {
        Route route = new Route();

        String routeType = resultSet.getString("route_type");
        route.setRouteType(RouteType.valueOf(routeType));
        route.setAttempts(resultSet.getInt("attempts"));
        if (resultSet.getDate("set_date") != null) {
            route.setSetDate(resultSet.getDate("set_date").toLocalDate());
        }

        return route;
    }
}
