package learn.rockClimbing.domain;

import learn.rockClimbing.data.RouteGradeRepository;
import learn.rockClimbing.models.GradingSystem;
import learn.rockClimbing.models.RouteGrade;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteGradeService {

    private final RouteGradeRepository routeGradeRepository;

    public RouteGradeService(RouteGradeRepository routeGradeRepository) {
        this.routeGradeRepository = routeGradeRepository;
    }

    public List<RouteGrade> findAllRouteGrades() {
        return routeGradeRepository.findAllRouteGrades();
    }

    public List<RouteGrade> findRouteGradeByGradingSystem(GradingSystem gradingSystem) {
        return routeGradeRepository.findRouteGradeByGradingSystem(gradingSystem);
    }

    public RouteGrade findRouteGradeById(int routeGradeId) {
        return routeGradeRepository.findRouteGradeById(routeGradeId);
    }
}
