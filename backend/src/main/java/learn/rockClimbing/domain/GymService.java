package learn.rockClimbing.domain;

import learn.rockClimbing.data.GymRepository;
import learn.rockClimbing.models.Gym;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GymService {

    private final GymRepository gymRepository;

    public GymService(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
    }

    public List<Gym> findAllGyms() {
        return gymRepository.findAllGyms();
    }

    public List<Gym> findGymsByClimberId(int climberId) {
        return gymRepository.findGymsByClimberId(climberId);
    }

    public Gym findGymById(int gymId) {
        return gymRepository.findGymById(gymId);
    }

    public Result<Gym> addGym(Gym gym) {
        Result<Gym> result = validate(gym);

        if (!result.isSuccess()) {
            return result;
        }

        // check for duplicate
        Gym finalGym = gym;
        boolean gymExists = gymRepository.findAllGyms().stream().anyMatch(i ->
            i.getName().equals(finalGym.getName())
            && i.getCity().equals(finalGym.getCity())
            && i.getState().equals(finalGym.getState())
        );

        if (gymExists) {
            result.addMessage("Gym already exists.", ResultType.INVALID);
            return result;
        }

        if (gym.getGymId() != 0) {
            result.addMessage("Gym Id cannot be set.", ResultType.INVALID);
            return result;
        }

        gym = gymRepository.addGym(gym);
        if (gym == null) {
            result.addMessage("Add gym failed.", ResultType.INVALID);
            return result;
        }

        result.setPayload(gym);
        return result;
    }

    public Result<Gym> editGym(Gym gym) {
        Result<Gym> result = validate(gym);

        if (!result.isSuccess()) {
            return result;
        }

        if (!gymRepository.editGym(gym)) {
            result.addMessage("Edit gym failed.", ResultType.INVALID);
        }

        return result;
    }

    public boolean deleteGymById(int gymId) {
        return gymRepository.deleteById(gymId);
    }

    private Result<Gym> validate(Gym gym) {
        Result<Gym> result = new Result<>();

        if (gym == null) {
            result.addMessage("Gym cannot by null.", ResultType.INVALID);
            return result;
        }

        if (gym.getName().length() <= 0) {
            result.addMessage("Gym name required.", ResultType.INVALID);
            return result;
        }

        if (gym.getCity().length() <= 0) {
            result.addMessage("Gym city required.", ResultType.INVALID);
            return result;
        }

        // TODO: Look into adding regex or maps API for location instead
        if (gym.getState().length() != 2) {
            result.addMessage("Gym state abbreviation not valid.", ResultType.INVALID);
        }

        return result;
    }
}
