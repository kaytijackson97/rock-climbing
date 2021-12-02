package learn.rockClimbing.data;

import learn.rockClimbing.models.Climber;

import java.util.List;

public interface ClimberRepository {
    List<Climber> findAllClimbers();

    Climber findByClimberId(int climberId);

    Climber addClimber(Climber climber);

    boolean editClimber(Climber climber);

    boolean deleteClimberById(int climberId);
}
