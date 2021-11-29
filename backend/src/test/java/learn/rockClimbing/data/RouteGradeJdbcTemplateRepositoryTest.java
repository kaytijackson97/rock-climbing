package learn.rockClimbing.data;

import learn.rockClimbing.models.GradingSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RouteGradeJdbcTemplateRepositoryTest {

    @Autowired
    RouteGradeRepository routeGradeRepository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setUp() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAllRouteGrades() {
        assertNotNull(routeGradeRepository.findAll());
    }

    @Test
    void shouldFindRouteGradesIfValidGradingSystem() {
        assertNotNull(routeGradeRepository.findRouteGradeByGradingSystem(GradingSystem.BOULDERING));
    }

    @Test
    void shouldFindRouteGradeIfValidId() {
        assertNotNull(routeGradeRepository.findRouteGradeById(1));
    }

    @Test
    void shouldNotFindByIdIfInvalidId() {
        assertNull(routeGradeRepository.findRouteGradeById(-1));
    }

}