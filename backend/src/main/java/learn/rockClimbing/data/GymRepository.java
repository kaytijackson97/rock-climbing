package learn.rockClimbing.data;

import learn.rockClimbing.models.Gym;

import java.util.List;

public interface GymRepository {
    List<Gym> findAll();

    List<Gym> findGymsByClimberId(int climberId);

    Gym findGymById(int gymId);

    Gym addGym(Gym gym);

    boolean editGym(Gym gym);

    boolean deleteById(int gymId);
}
