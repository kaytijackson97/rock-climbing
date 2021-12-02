package learn.rockClimbing.domain;

import learn.rockClimbing.data.RouteRepository;
import learn.rockClimbing.models.Route;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RouteService {
    private final RouteRepository routeRepository;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public List<Route> findAllRoutes() {
        return routeRepository.findAllRoutes();
    }

    public Route findRouteById(int routeId) {
        return routeRepository.findRouteById(routeId);
    }

    public List<Route> findRoutesByGym(int gymId) {
        return routeRepository.findRoutesByGym(gymId);
    }

    public List<Route> findRoutesByClimber(int climberId) {
        return routeRepository.findRoutesByClimber(climberId);
    }

    public List<Route> findRoutesByGymAndClimber(int gymId, int climberId) {
        return routeRepository.findRoutesByGymAndClimber(gymId, climberId);
    }

    public Result<Route> addRoute(Route route) {
        Result<Route> result = validateRoute(route);

        if (!result.isSuccess()) {
            return result;
        }

        if (route.getRouteId() != 0) {
            result.addMessage("Route Id cannot be set.", ResultType.INVALID);
            return result;
        }

        route = routeRepository.addRoute(route);
        if (route == null) {
            result.addMessage("Add route failed.", ResultType.INVALID);
            return result;
        }

        result.setPayload(route);
        return result;
    }

    public Result<Route> editRoute(Route route) {
        Result<Route> result = validateRoute(route);

        if (!result.isSuccess()) {
            return result;
        }

        if (!routeRepository.editRoute(route)) {
            result.addMessage("Edit route failed.", ResultType.INVALID);
        }
            return result;
    }

    public boolean deleteRouteById(int routeId) {
        return routeRepository.deleteRouteById(routeId);
    }

    private Result<Route> validateRoute(Route route) {
        Result<Route> result = new Result<>();

        if (route == null) {
            result.addMessage("Route cannot by null.", ResultType.INVALID);
            return result;
        }

        if (route.getGym() == null) {
            result.addMessage("Gym is required.", ResultType.INVALID);
            return result;
        }

        if (route.getRouteGrade() == null) {
            result.addMessage("Route grade is required.", ResultType.INVALID);
            return result;
        }

        if (route.getRouteType() == null) {
            result.addMessage("Route type is required.", ResultType.INVALID);
            return result;
        }

        if (route.getAttempts() < 0 || route.getAttempts() > 999) {
            result.addMessage("Attempts must be between 0 and 999.", ResultType.INVALID);
            return result;
        }

        if (route.getSetDate().isBefore(LocalDate.of(1987, 1, 1)) ||
            route.getSetDate().isAfter(LocalDate.now())
        ) {
            result.addMessage("Set date must be between January 1, 1987 and today.", ResultType.INVALID);
            return result;
        }

        return result;
    }
}
