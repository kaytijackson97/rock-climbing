package learn.rockClimbing.domain;

import learn.rockClimbing.data.GymRepository;
import learn.rockClimbing.models.Gym;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class GymServiceTest {

    @Autowired
    GymService gymService;

    @MockBean
    GymRepository gymRepository;

    @Test
    void shouldAddValidGym() {
        Gym gym = createGym(4);
        gym.setGymId(0);
        Gym mockout = createGym(4);

        when(gymRepository.addGym(gym)).thenReturn(mockout);
        Result<Gym> result = gymService.addGym(gym);
        assertEquals(0, result.getMessages().size());
    }

    @Test
    void shouldNotAddNullGym() {
        Result<Gym> result = gymService.addGym(null);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals(1, result.getMessages().size());
    }

    @Test
    void shouldNotAddGymIfEmptyFields() {
        Gym gymWithNoName = createGym(4);
        gymWithNoName.setName("");

        Result<Gym> result = gymService.addGym(gymWithNoName);
        assertEquals(1, result.getMessages().size());

        Gym gymWithNoCity = createGym(4);
        gymWithNoCity.setCity("");

        result = gymService.addGym(gymWithNoCity);
        assertEquals(1, result.getMessages().size());
    }

    @Test
    void shouldNotAddGymIfInvalidState() {
        Gym gymWithNoName = createGym(4);
        gymWithNoName.setState("test");

        Result<Gym> result = gymService.addGym(gymWithNoName);
        assertEquals(1, result.getMessages().size());
    }

    @Test
    void shouldNotAddGymIfDuplicate() {
        Gym gym = createGym(4);
        Gym mockout = createGym(4);

        when(gymRepository.findAllGyms()).thenReturn(List.of(mockout));
        Result<Gym> result = gymService.addGym(gym);
        assertEquals(1, result.getMessages().size());
    }

    @Test
    void shouldNotAddGymIfSetGymId() {
        Gym gymWithSetGymId = createGym(4);
        gymWithSetGymId.setGymId(1);

        Result<Gym> result = gymService.addGym(gymWithSetGymId);
        assertEquals(1, result.getMessages().size());
    }

    @Test
    void shouldReturnErrorIfAddGymFails() {
        Gym gym = createGym(4);
        gym.setGymId(0);

        when(gymRepository.addGym(gym)).thenReturn(null);
        Result<Gym> result = gymService.addGym(gym);
        assertEquals(1, result.getMessages().size());
    }

    @Test
    void shouldEditValidGym() {
        Gym gym = createGym(2);
        when(gymRepository.editGym(gym)).thenReturn(true);
        Result<Gym> result = gymService.editGym(gym);
        assertEquals(0, result.getMessages().size());
    }

    @Test
    void shouldNotEditNullGym() {
        Result<Gym> result = gymService.editGym(null);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals(1, result.getMessages().size());
    }

    @Test
    void shouldNotEditGymIfEmptyFields() {
        Gym gymWithNoName = createGym(2);
        gymWithNoName.setName("");
        Result<Gym> result = gymService.editGym(gymWithNoName);
        assertEquals(1, result.getMessages().size());

        Gym gymWithNoCity = createGym(2);
        gymWithNoCity.setCity("");
        result = gymService.editGym(gymWithNoCity);
        assertEquals(1, result.getMessages().size());
    }

    @Test
    void shouldNotEditGymIfInvalidState() {
        Gym gymWithNoName = createGym(2);
        gymWithNoName.setState("Test");
        Result<Gym> result = gymService.editGym(gymWithNoName);
        assertEquals(1, result.getMessages().size());
    }

    @Test
    void shouldReturnInvalidResultTypeIfEditGymFails() {
        Gym gym = createGym(2);
        when(gymRepository.editGym(gym)).thenReturn(false);
        Result<Gym> result = gymService.editGym(gym);
        assertEquals(1, result.getMessages().size());
    }

    @Test
    void shouldDeleteGymIfValidId() {
        when(gymRepository.deleteById(3)).thenReturn(true);
        assertTrue(gymService.deleteGymById(3));
    }

    @Test
    void shouldNotDeleteGymIfInvalidId() {
        when(gymRepository.deleteById(5)).thenReturn(false);
        assertFalse(gymService.deleteGymById(5));
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