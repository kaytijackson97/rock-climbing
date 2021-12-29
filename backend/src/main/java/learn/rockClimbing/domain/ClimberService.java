package learn.rockClimbing.domain;

import learn.rockClimbing.data.ClimberRepository;
import learn.rockClimbing.data.ClimberRouteRepository;
import learn.rockClimbing.models.Climber;
import learn.rockClimbing.models.ClimberRoute;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClimberService {
    private final ClimberRepository climberRepository;
    private final ClimberRouteRepository climberRouteRepository;

    public ClimberService(ClimberRepository climberRepository, ClimberRouteRepository climberRouteRepository) {
        this.climberRepository = climberRepository;
        this.climberRouteRepository = climberRouteRepository;
    }

    public List<Climber> findAllClimbers() {
        return climberRepository.findAllClimbers();
    }

    public Climber findByClimberId(int climberId) {
        return climberRepository.findByClimberId(climberId);
    }

    public Result<Climber> addClimber(Climber climber) {
        Result<Climber> result = validateClimber(climber);

        if (!result.isSuccess()) {
            return result;
        }

        if (climber.getClimberId() != 0) {
            result.addMessage("Climber Id cannot be set.", ResultType.INVALID);
            return result;
        }

        climber = climberRepository.addClimber(climber);
        if (climber == null) {
            result.addMessage("Add climber failed.", ResultType.INVALID);
            return result;
        }

        result.setPayload(climber);
        return result;
    }

    public Result<Climber> editClimber(Climber climber) {
        Result<Climber> result = validateClimber(climber);

        if (!result.isSuccess()) {
            return result;
        }

        if (!climberRepository.editClimber(climber)) {
            result.addMessage("Edit climber failed.", ResultType.INVALID);
        }

        return result;
    }

    public boolean deleteClimberById(int climberId) {
        return climberRepository.deleteClimberById(climberId);
    }

    public Result<Void> addRoute(ClimberRoute climberRoute) {
        Result<Void> result = validateClimberRoute(climberRoute);
        if (!result.isSuccess()) {
            return result;
        }

        if (!climberRouteRepository.add(climberRoute)) {
            result.addMessage("Route not added.", ResultType.INVALID);
        }

        return result;
    }

    public boolean deleteRouteByKey(int climberId, int routeId) {
        return climberRouteRepository.deleteByKey(climberId, routeId);
    }

    private Result<Climber> validateClimber(Climber climber) {
        Result<Climber> result = new Result<>();

        if (climber == null) {
            result.addMessage("Climber cannot be null.", ResultType.INVALID);
            return result;
        }

        if (climber.getName().length() <= 0) {
            result.addMessage("Climber name required.", ResultType.INVALID);
            return result;
        }

        //TODO: Change Age to birthdate
        if (climber.getAge() <= 5 || climber.getAge() >= 99) {
            result.addMessage("Climber age must be between 5 and 99.", ResultType.INVALID);
            return result;
        }

        if (climber.getMonthsClimbing() < 0 || climber.getMonthsClimbing() > climber.getAge() * 12) {
            String message = String.format("Months climbing must be between 0 and %s", climber.getAge() * 12);
            result.addMessage(message, ResultType.INVALID);
            return result;
        }
        return result;
    }

    private Result<Void> validateClimberRoute(ClimberRoute climberRoute) {
        Result<Void> result = new Result<>();
        if (climberRoute == null) {
            result.addMessage("climberRoute cannot be null.", ResultType.INVALID);
            return result;
        }

        if (climberRoute.getClimber() == null) {
            result.addMessage("Climber cannot be null.", ResultType.INVALID);
            return result;
        }

        if (climberRoute.getRoute() == null) {
            result.addMessage("Route cannot be null.", ResultType.INVALID);
        }

        return result;
    }
}
