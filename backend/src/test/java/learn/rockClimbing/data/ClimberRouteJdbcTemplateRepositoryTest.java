package learn.rockClimbing.data;

import learn.rockClimbing.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ClimberRouteJdbcTemplateRepositoryTest {

    @Autowired
    ClimberRouteRepository climberRouteRepository;

    @Autowired
    RouteGradeRepository routeGradeRepository;

    @Autowired
    GymRepository gymRepository;

    @Autowired
    ClimberRepository climberRepository;

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldAdd() {
        ClimberRoute climberRoute = makeClimberRoute();

        assertTrue(climberRouteRepository.add(climberRoute));

        try {
            climberRouteRepository.add(climberRoute);
            fail("cannot add an agent to an agency twice.");
        } catch (DataAccessException ex) {
            // this is expected.
        }
    }

    @Test
    void shouldDelete() {
        ClimberRoute climberRoute = makeClimberRoute();
        climberRouteRepository.add(climberRoute);
        assertTrue(climberRouteRepository.deleteByKey(6, 6));
        assertFalse(climberRouteRepository.deleteByKey(6, 6));
    }

    private Route makeRoute() {
        Route route = new Route();

        route.setRouteId(6);
        route.setGym(gymRepository.findGymById(1));
        route.setRouteGrade(routeGradeRepository.findRouteGradeById(1));
        route.setRouteType(RouteType.BOULDERING.getRoute());
        route.setAttempts(8);
        route.setSetDate(LocalDate.of(2021, 5, 13));
        routeRepository.addRoute(route);

        return route;
    }

    private Climber makeClimber() {
        Climber climber = new Climber();

        climber.setClimberId(6);
        climber.setName("Test Name");
        climber.setAge(25);
        climber.setMonthsClimbing(12);

        climberRepository.addClimber(climber);

        return climber;
    }

    private ClimberRoute makeClimberRoute() {
        ClimberRoute climberRoute = new ClimberRoute();
        climberRoute.setRoute(makeRoute());
        climberRoute.setClimber(makeClimber());

        return climberRoute;
    }
}