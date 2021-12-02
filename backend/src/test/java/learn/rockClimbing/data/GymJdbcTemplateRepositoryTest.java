package learn.rockClimbing.data;

import learn.rockClimbing.models.Gym;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GymJdbcTemplateRepositoryTest {

    @Autowired
    GymRepository gymRepository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setUp() {
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        assertTrue(gymRepository.findAllGyms().size() >= 2);
    }

    @Test
    void shouldFindGymsByClimberIdIfValidId() {
        assertNotNull(gymRepository.findGymsByClimberId(1));
    }

    @Test
    void shouldFindGymByIdIfValidId() {
        assertNotNull(gymRepository.findGymById(1));
    }

    @Test
    void shouldReturnNullIfFindGymByIdIsInvalidId() {
        assertNull(gymRepository.findGymById(5));
    }

    @Test
    void shouldAddGymIfValid() {
        Gym expected = createGym(4);
        Gym actual = gymRepository.addGym(expected);

        assertNotNull(actual);
        assertEquals(4, actual.getGymId());
    }

    @Test
    void shouldNotAddGymIfNull() {
        assertNull(gymRepository.addGym(null));
    }

    @Test
    void shouldNotAddGymIfNullFields() {
        Gym gymWithNoName = createGym(4);
        gymWithNoName.setName(null);
        assertNull(gymRepository.addGym(gymWithNoName));

        Gym gymWithNoCity = createGym(4);
        gymWithNoCity.setCity(null);
        assertNull(gymRepository.addGym(gymWithNoCity));

        Gym gymWithNoState = createGym(4);
        gymWithNoState.setState(null);
        assertNull(gymRepository.addGym(gymWithNoState));
    }

    @Test
    void shouldEditGymIfValid() {
        Gym expected = gymRepository.findGymById(2);
        expected.setName("New Test Name");

        boolean actual = gymRepository.editGym(expected);

        assertTrue(actual);
        assertEquals("New Test Name", gymRepository.findGymById(2).getName());
    }

    @Test
    void shouldNotEditGymIfInvalidId() {
        Gym gym = createGym(5);
        assertFalse(gymRepository.editGym(gym));
    }

    @Test
    void shouldNotEditGymIfNull() {
        assertFalse(gymRepository.editGym(null));
    }

    @Test
    void shouldNotEditGymIfNullFields() {
        Gym gymWithNoName = gymRepository.findGymById(2);
        gymWithNoName.setName(null);
        assertFalse(gymRepository.editGym(gymWithNoName));

        Gym gymWithNoCity = gymRepository.findGymById(2);
        gymWithNoCity.setCity(null);
        assertFalse(gymRepository.editGym(gymWithNoCity));

        Gym gymWithNoState = gymRepository.findGymById(2);
        gymWithNoState.setState(null);
        assertFalse(gymRepository.editGym(gymWithNoState));
    }

    @Test
    void shouldDeleteGymIfValid() {
        assertTrue(gymRepository.deleteById(3));
    }

    @Test
    void shouldNotDeleteGymIfInvalidId() {
        assertFalse(gymRepository.deleteById(5));
    }

    private Gym createGym(int gymId) {
        Gym gym = new Gym();

        gym.setGymId(gymId);
        gym.setName("test name");
        gym.setCity("test city");
        gym.setState("VA");

        return gym;
    }
}