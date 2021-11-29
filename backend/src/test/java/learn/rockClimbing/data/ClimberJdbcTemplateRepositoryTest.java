package learn.rockClimbing.data;

import learn.rockClimbing.models.Climber;
import learn.rockClimbing.models.Gym;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClimberJdbcTemplateRepositoryTest {

    @Autowired
    ClimberRepository climberRepository;

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    GymRepository gymRepository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setUp(){
        knownGoodState.set();
    }

    @Test
    void shouldFindAllClimbers() {
        assertNotNull(climberRepository.findAll());
    }

    @Test
    void shouldFindClimberByValidId() {
        assertNotNull(climberRepository.findByClimberId(1));
    }

    @Test
    void shouldReturnNullIfFindClimberByIdIsInvalidId() {
        assertNull(climberRepository.findByClimberId(5));
    }

    @Test
    void shouldAddValidClimber() {
        Climber expected = createClimber(4);
        Climber actual = climberRepository.addClimber(expected);

        assertNotNull(actual);
        assertEquals(4, actual.getClimberId());
    }

    @Test
    void shouldNotAddNullClimber() {
        assertNull(climberRepository.addClimber(null));
    }

    @Test
    void shouldNotAddClimberWithNullName() {
        Climber climberWithNoName = createClimber(4);
        climberWithNoName.setName(null);
        assertNull(climberRepository.addClimber(climberWithNoName));
    }

    @Test
    void shouldEditValidClimber() {
        Climber climber = climberRepository.findByClimberId(2);
        climber.setName("New Test Name");
        assertTrue(climberRepository.editClimber(climber));
    }

    @Test
    void shouldNotEditInvalidClimber() {
        Climber climber = createClimber(5);
        assertFalse(climberRepository.editClimber(climber));
    }

    @Test
    void shouldNotEditNullClimber() {
        assertFalse(climberRepository.editClimber(null));
    }

    @Test
    void shouldNotEditClimberWithNullFields() {
        Climber climberWithNoName = climberRepository.findByClimberId(2);
        climberWithNoName.setName(null);
        assertFalse(climberRepository.editClimber(climberWithNoName));
    }

    @Test
    void shouldDeleteValidClimber() {
        assertTrue(climberRepository.deleteClimberById(3));
    }

    @Test
    void shouldNotDeleteInvalidClimber() {
        assertFalse(climberRepository.deleteClimberById(5));
    }

    private Climber createClimber(int climberId) {
        Climber climber = new Climber();

        climber.setClimberId(climberId);
        climber.setName("Test Name");
        climber.setAge(25);
        climber.setMonthsClimbing(12);
        climber.setClimbs(routeRepository.findRoutesByClimber(climberId));
        climber.setGyms(gymRepository.findGymsByClimberId(climberId));

        return climber;
    }
}