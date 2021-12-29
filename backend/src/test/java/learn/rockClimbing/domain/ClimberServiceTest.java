package learn.rockClimbing.domain;

import learn.rockClimbing.data.ClimberRepository;
import learn.rockClimbing.data.ClimberRouteRepository;
import learn.rockClimbing.models.Climber;
import learn.rockClimbing.models.ClimberRoute;
import learn.rockClimbing.models.Route;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClimberServiceTest {

    @Autowired
    ClimberService climberService;

    @MockBean
    ClimberRepository climberRepository;

    @MockBean
    ClimberRouteRepository climberRouteRepository;

    @Test
    void shouldAddValidClimber() {
        Climber climber = createClimber(4);
        climber.setClimberId(0);
        Climber mockout = createClimber(4);

        when(climberRepository.addClimber(climber)).thenReturn(mockout);
        Result<Climber> result = climberService.addClimber(climber);
        assertEquals(0, result.getMessages().size());
    }

    @Test
    void shouldNotAddNullClimber() {
        Result<Climber> result = climberService.addClimber(null);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals(1, result.getMessages().size());
    }

    @Test
    void shouldNotAddClimberIfEmptyName() {
        Climber climberWithNoName = createClimber(4);
        climberWithNoName.setName("");

        Result<Climber> result = climberService.addClimber(climberWithNoName);
        assertEquals(1, result.getMessages().size());
    }

    @Test
    void shouldNotAddClimberIfInvalidAge() {
        Climber climberAgeFour = createClimber(4);
        climberAgeFour.setAge(4);

        Result<Climber> result = climberService.addClimber(climberAgeFour);
        assertEquals(1, result.getMessages().size());

        Climber climberAgeOneHundred = createClimber(4);
        climberAgeOneHundred.setAge(100);

        result = climberService.addClimber(climberAgeOneHundred);
        assertEquals(1, result.getMessages().size());
    }

    @Test
    void shouldNotAddClimberIfInvalidMonthsClimbing() {
        Climber climberNegativeMonthsClimbing = createClimber(4);
        climberNegativeMonthsClimbing.setMonthsClimbing(-1);

        Result<Climber> result = climberService.addClimber(climberNegativeMonthsClimbing);
        assertEquals(1, result.getMessages().size());

        Climber climberMonthsClimbingGreaterThanAge = createClimber(4);
        climberMonthsClimbingGreaterThanAge.setMonthsClimbing(
                climberMonthsClimbingGreaterThanAge.getAge() * 13
        );

        result = climberService.addClimber(climberMonthsClimbingGreaterThanAge);
        assertEquals(1, result.getMessages().size());
    }

    @Test
    void shouldNotAddClimberIfSetClimberId() {
        Climber climberWithSetGymId = createClimber(4);
        climberWithSetGymId.setClimberId(1);

        Result<Climber> result = climberService.addClimber(climberWithSetGymId);
        assertEquals(1, result.getMessages().size());
    }

    @Test
    void shouldReturnErrorIfAddClimberFails() {
        Climber climber = createClimber(4);
        climber.setClimberId(0);

        when(climberRepository.addClimber(climber)).thenReturn(null);
        Result<Climber> result = climberService.addClimber(climber);
        assertEquals(1, result.getMessages().size());
    }

    @Test
    void shouldEditValidClimber() {
        Climber climber = createClimber(2);
        when(climberRepository.editClimber(climber)).thenReturn(true);
        Result<Climber> result = climberService.editClimber(climber);
        assertEquals(0, result.getMessages().size());
    }

    @Test
    void shouldNotEditNullGym() {
        Result<Climber> result = climberService.editClimber(null);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals(1, result.getMessages().size());
    }

    @Test
    void shouldNotEditClimberIfEmptyName() {
        Climber climberWithNoName = createClimber(4);
        climberWithNoName.setName("");

        Result<Climber> result = climberService.editClimber(climberWithNoName);
        assertEquals(1, result.getMessages().size());
    }

    @Test
    void shouldNotEditClimberIfInvalidAge() {
        Climber climberAgeFour = createClimber(4);
        climberAgeFour.setAge(4);

        Result<Climber> result = climberService.editClimber(climberAgeFour);
        assertEquals(1, result.getMessages().size());

        Climber climberAgeOneHundred = createClimber(4);
        climberAgeOneHundred.setAge(100);

        result = climberService.editClimber(climberAgeOneHundred);
        assertEquals(1, result.getMessages().size());
    }

    @Test
    void shouldNotEditClimberIfInvalidMonthsClimbing() {
        Climber climberNegativeMonthsClimbing = createClimber(4);
        climberNegativeMonthsClimbing.setMonthsClimbing(-1);

        Result<Climber> result = climberService.editClimber(climberNegativeMonthsClimbing);
        assertEquals(1, result.getMessages().size());

        Climber climberMonthsClimbingGreaterThanAge = createClimber(4);
        climberMonthsClimbingGreaterThanAge.setMonthsClimbing(
                climberMonthsClimbingGreaterThanAge.getAge() * 13
        );

        result = climberService.editClimber(climberMonthsClimbingGreaterThanAge);
        assertEquals(1, result.getMessages().size());
    }

    @Test
    void shouldReturnInvalidResultTypeIfEditClimberFails() {
        Climber climber = createClimber(2);
        when(climberRepository.editClimber(climber)).thenReturn(false);
        Result<Climber> result = climberService.editClimber(climber);
        assertEquals(1, result.getMessages().size());
    }

    @Test
    void shouldDeleteClimberIfValidId() {
        when(climberRepository.deleteClimberById(3)).thenReturn(true);
        assertTrue(climberService.deleteClimberById(3));
    }

    @Test
    void shouldNotDeleteClimberIfInvalidId() {
        when(climberRepository.deleteClimberById(5)).thenReturn(false);
        assertFalse(climberService.deleteClimberById(5));
    }

    @Test
    void shouldAddRouteIfValid() {
        ClimberRoute climberRoute = new ClimberRoute();
        Climber climber = new Climber();
        Route route = new Route();

        climberRoute.setClimber(climber);
        climberRoute.setRoute(route);
        when(climberRouteRepository.add(climberRoute)).thenReturn(true);
        Result<Void> result = climberService.addRoute(climberRoute);
        assertEquals(0, result.getMessages().size());
    }

    @Test
    void shouldNotAddRouteIfNull() {
        Result<Void> result = climberService.addRoute(null);
        assertEquals(1, result.getMessages().size());
    }

    @Test
    void shouldNotAddRouteIfClimberIsNull() {
        ClimberRoute climberRoute = new ClimberRoute();
        Route route = new Route();

        climberRoute.setClimber(null);
        climberRoute.setRoute(route);

        Result<Void> result = climberService.addRoute(climberRoute);
        assertEquals(1, result.getMessages().size());
    }

    @Test
    void shouldNotAddRouteIfRouteIsNull() {
        ClimberRoute climberRoute = new ClimberRoute();
        Climber climber = new Climber();

        climberRoute.setClimber(climber);
        climberRoute.setRoute(null);

        Result<Void> result = climberService.addRoute(climberRoute);
        assertEquals(1, result.getMessages().size());
    }

    private Climber createClimber(int climberId) {
        Climber climber = new Climber();

        climber.setClimberId(climberId);
        climber.setName("test name");
        climber.setAge(25);
        climber.setMonthsClimbing(1);

        return climber;
    }

}