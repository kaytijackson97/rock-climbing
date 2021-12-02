package learn.rockClimbing.domain;

import learn.rockClimbing.data.RouteRepository;
import learn.rockClimbing.models.Gym;
import learn.rockClimbing.models.Route;
import learn.rockClimbing.models.RouteGrade;
import learn.rockClimbing.models.RouteType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class RouteServiceTest {

    @Autowired
    RouteService routeService;

    @MockBean
    RouteRepository routeRepository;

    @Test
    void shouldAddValidRoute() {
        Route route = createRoute(4);
        route.setRouteId(0);
        Route mockout = createRoute(4);

        when(routeRepository.addRoute(route)).thenReturn(mockout);
        Result<Route> result = routeService.addRoute(route);
        assertEquals(0, result.getMessages().size());
    }

    @Test
    void shouldNotAddNullRoute() {
        Result<Route> result = routeService.addRoute(null);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals(1, result.getMessages().size());
    }

    @Test
    void shouldNotAddRouteWithNullFields() {
        Route routeWithNullGym = createRoute(4);
        routeWithNullGym.setGym(null);

        Result<Route> result = routeService.addRoute(routeWithNullGym);
        assertEquals(1, result.getMessages().size());

        Route routeWithNullRouteGrade = createRoute(4);
        routeWithNullRouteGrade.setGym(null);

        result = routeService.addRoute(routeWithNullRouteGrade);
        assertEquals(1, result.getMessages().size());

        Route routeWithNullRouteType = createRoute(4);
        routeWithNullRouteType.setRouteType(null);

        result = routeService.addRoute(routeWithNullRouteType);
        assertEquals(1, result.getMessages().size());
    }

    @Test
    void shouldNotAddRouteWithInvalidSetDate() {
        Route routeWithOldSetDate = createRoute(4);
        routeWithOldSetDate.setSetDate(LocalDate.of(1986,12,13));

        Result<Route> result = routeService.addRoute(routeWithOldSetDate);
        assertEquals(1, result.getMessages().size());

        Route routeWithFutureSetDate = createRoute(4);
        routeWithOldSetDate.setSetDate(LocalDate.now().plusDays(1));

        result = routeService.addRoute(routeWithFutureSetDate);
        assertEquals(1, result.getMessages().size());
    }

    @Test
    void shouldNotAddRouteWithInvalidAttempts() {
        Route routeWithNegativeAttempts = createRoute(4);
        routeWithNegativeAttempts.setAttempts(-1);

        Result<Route> result = routeService.addRoute(routeWithNegativeAttempts);
        assertEquals(1, result.getMessages().size());

        Route routeWithOneThousandAttempts = createRoute(4);
        routeWithNegativeAttempts.setAttempts(1000);

        result = routeService.addRoute(routeWithOneThousandAttempts);
        assertEquals(1, result.getMessages().size());
    }

    @Test
    void shouldNotAddRouteIfSetRouteId() {
        Route routeWithNegativeAttempts = createRoute(4);
        routeWithNegativeAttempts.setRouteId(1);

        Result<Route> result = routeService.addRoute(routeWithNegativeAttempts);
        assertEquals(1, result.getMessages().size());
    }

    @Test
    void shouldReturnErrorIfAddGymFails() {
        Route route = createRoute(4);
        route.setRouteId(0);

        when(routeRepository.addRoute(route)).thenReturn(null);
        Result<Route> result = routeService.addRoute(route);
        assertEquals(1, result.getMessages().size());
    }

    @Test
    void shouldEditValidGym() {
        Route route = createRoute(2);

        when(routeRepository.editRoute(route)).thenReturn(true);
        Result<Route> result = routeService.editRoute(route);
        assertEquals(0, result.getMessages().size());
    }

    @Test
    void shouldNotEditNullRoute() {
        Result<Route> result = routeService.editRoute(null);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals(1, result.getMessages().size());
    }

    @Test
    void shouldNotEditRouteWithNullFields() {
        Route routeWithNullGym = createRoute(4);
        routeWithNullGym.setGym(null);

        Result<Route> result = routeService.editRoute(routeWithNullGym);
        assertEquals(1, result.getMessages().size());

        Route routeWithNullRouteGrade = createRoute(4);
        routeWithNullRouteGrade.setRouteGrade(null);

        result = routeService.editRoute(routeWithNullRouteGrade);
        assertEquals(1, result.getMessages().size());

        Route routeWithNullRouteType = createRoute(4);
        routeWithNullRouteType.setRouteType(null);

        result = routeService.editRoute(routeWithNullRouteType);
        assertEquals(1, result.getMessages().size());
    }

    @Test
    void shouldNotEditRouteWithInvalidSetDate() {
        Route routeWithOldSetDate = createRoute(4);
        routeWithOldSetDate.setSetDate(LocalDate.of(1986,12,13));

        Result<Route> result = routeService.editRoute(routeWithOldSetDate);
        assertEquals(1, result.getMessages().size());

        Route routeWithFutureSetDate = createRoute(4);
        routeWithOldSetDate.setSetDate(LocalDate.now().plusDays(1));

        result = routeService.editRoute(routeWithFutureSetDate);
        assertEquals(1, result.getMessages().size());
    }

    @Test
    void shouldNotEditRouteWithInvalidAttempts() {
        Route routeWithNegativeAttempts = createRoute(4);
        routeWithNegativeAttempts.setAttempts(-1);

        Result<Route> result = routeService.editRoute(routeWithNegativeAttempts);
        assertEquals(1, result.getMessages().size());

        Route routeWithOneThousandAttempts = createRoute(4);
        routeWithNegativeAttempts.setAttempts(1000);

        result = routeService.editRoute(routeWithOneThousandAttempts);
        assertEquals(1, result.getMessages().size());
    }

    @Test
    void shouldDeleteRouteIfValidId() {
        when(routeRepository.deleteRouteById(3)).thenReturn(true);
        assertTrue(routeService.deleteRouteById(3));
    }

    @Test
    void shouldNotDeleteGymIfInvalidId() {
        when(routeRepository.deleteRouteById(5)).thenReturn(false);
        assertFalse(routeService.deleteRouteById(5));
    }

    private Route createRoute(int routeId) {
        Route route = new Route();
        Gym gym = new Gym();
        RouteGrade routeGrade = new RouteGrade();

        route.setRouteId(routeId);
        route.setGym(gym);
        route.setRouteGrade(routeGrade);
        route.setRouteType(RouteType.BOULDERING);
        route.setAttempts(1);
        route.setSetDate(LocalDate.now().minusDays(5));

        return route;
    }

}