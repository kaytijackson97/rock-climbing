package learn.rockClimbing.data;

import learn.rockClimbing.models.Gym;
import learn.rockClimbing.models.Route;
import learn.rockClimbing.models.RouteGrade;
import learn.rockClimbing.models.RouteType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RouteJdbcTemplateRepositoryTest {

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    GymRepository gymRepository;

    @Autowired
    RouteGradeRepository routeGradeRepository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setUp() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        assertNotNull(routeRepository.findAllRoutes());
    }

    @Test
    void shouldFindRoutesByGym() {
        assertNotNull(routeRepository.findRoutesByGym(1));
    }

    @Test
    void shouldFindRoutesByClimber() {
        assertNotNull(routeRepository.findRoutesByClimber(1));
    }

    @Test
    void shouldFindRoutesByGymAndClimber() {
        assertNotNull(routeRepository.findRoutesByGymAndClimber(1, 1));
    }

    @Test
    void shouldFindRouteByIdIfValidId() {
        assertNotNull(routeRepository.findRouteById(1));
    }

    @Test
    void shouldNotFindByIdIfInvalidId() {
        assertNull(routeRepository.findRouteById(5));
    }

    @Test
    void shouldAddValidRoute() {
        Route expected = createRoute(4);
        Route actual = routeRepository.addRoute(expected);
        assertNotNull(actual);
        assertEquals(4, actual.getRouteId());
    }

    @Test
    void shouldNotAddNullRoute() {
        assertNull(routeRepository.addRoute(null));
    }

    @Test
    void shouldNotAddIfNullFields() {

    }

    @Test
    void shouldEditRouteIfValid() {
        Route expected = routeRepository.findRouteById(2);
        expected.setSetDate(LocalDate.of(2031, 5, 13));
        assertTrue(routeRepository.editRoute(expected));

        Route actual = routeRepository.findRouteById(2);
        assertEquals(expected.getSetDate(), actual.getSetDate());
    }

    @Test
    void shouldNotEditIfInvalidRouteId() {
        Route route = routeRepository.findRouteById(2);
        route.setRouteId(5);
        route.setRouteType(RouteType.TOP_ROPE);
        assertFalse(routeRepository.editRoute(route));
    }

    @Test
    void shouldNotEditIfNullFields() {
        assertFalse(routeRepository.editRoute(null));
    }

    @Test
    void shouldDeleteIfValidId() {
        assertTrue(routeRepository.deleteRouteById(3));
    }

    @Test
    void shouldNotDeleteIfInvalidId() {
        assertFalse(routeRepository.deleteRouteById(4));
    }

    private Route createRoute(int routeId) {
        Route route = new Route();

        Gym gym = gymRepository.findGymById(1);
        RouteGrade routeGrade = routeGradeRepository.findRouteGradeById(1);

        route.setRouteId(routeId);
        route.setGym(gym);
        route.setRouteGrade(routeGrade);
        route.setRouteType(RouteType.BOULDERING);
        route.setAttempts(8);
        route.setSetDate(LocalDate.of(2021, 5, 13));

        return route;
    }
}
