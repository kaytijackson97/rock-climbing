package learn.rockClimbing.data.mappers;

import learn.rockClimbing.models.RouteGrade;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RouteGradeMapper implements RowMapper<RouteGrade> {
    @Override
    public RouteGrade mapRow(ResultSet resultSet, int i) throws SQLException {
        RouteGrade routeGrade = new RouteGrade();
        routeGrade.setRouteGradeId(resultSet.getInt("route_grade_id"));
        routeGrade.setGradingSystem(resultSet.getString("grading_system"));
        routeGrade.setGradeLevel(resultSet.getString("grade_level"));

        return routeGrade;
    }
}
