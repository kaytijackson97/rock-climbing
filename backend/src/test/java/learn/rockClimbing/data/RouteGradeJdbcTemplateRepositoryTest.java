package learn.rockClimbing.data;

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
    void shouldFindRouteGradesIfValidGradingSystem() {

    }

    @Test
    void shouldFindRouteGradeIfValidId() {

    }

    @Test
    void shouldNotFindByIdIfInvalidId() {

    }

}