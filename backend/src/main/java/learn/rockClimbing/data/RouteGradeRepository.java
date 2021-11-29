package learn.rockClimbing.data;

import learn.rockClimbing.models.GradingSystem;
import learn.rockClimbing.models.RouteGrade;

import java.util.List;

public interface RouteGradeRepository {
    List<RouteGrade> findAll();

    List<RouteGrade> findRouteGradeByGradingSystem(GradingSystem gradingSystem);

    RouteGrade findRouteGradeById(int routeGradeId);
}
