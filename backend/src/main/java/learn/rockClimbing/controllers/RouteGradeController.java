package learn.rockClimbing.controllers;

import learn.rockClimbing.domain.RouteGradeService;
import learn.rockClimbing.models.GradingSystem;
import learn.rockClimbing.models.RouteGrade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/route-grade")
public class RouteGradeController {
    private final RouteGradeService routeGradeService;

    public RouteGradeController(RouteGradeService routeGradeService) {
        this.routeGradeService = routeGradeService;
    }

    @GetMapping
    public List<RouteGrade> findAllRouteGrades() {
        return routeGradeService.findAllRouteGrades();
    }

    @GetMapping("/grading-system/{gradingSystemString}")
    public ResponseEntity<Object> findAllRouteGradesByGradingSystem(@PathVariable String gradingSystemString) {
        GradingSystem gradingSystem = GradingSystem.valueOf(gradingSystemString);

        List<RouteGrade> routeGrades = routeGradeService.findRouteGradeByGradingSystem(gradingSystem);
        if (routeGrades.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(routeGrades, HttpStatus.OK);
    }

    @GetMapping("/{routeGradeId}")
    public ResponseEntity<Object> findRouteById(@PathVariable int routeGradeId) {
        RouteGrade routeGrade = routeGradeService.findRouteGradeById(routeGradeId);
        if (routeGrade == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(routeGrade, HttpStatus.OK);
    }
}
