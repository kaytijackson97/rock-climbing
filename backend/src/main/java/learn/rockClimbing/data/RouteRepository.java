package learn.rockClimbing.data;

import learn.rockClimbing.models.Route;

import java.util.List;

public interface RouteRepository {
    List<Route> findAll();

    Route findRouteById(int routeId);

    List<Route> findRoutesByGym(int gymId);

    List<Route> findRoutesByClimber(int climberId);

    List<Route> findRoutesByGymAndClimber(int gymId, int climberId);

    Route addRoute(Route route);

    boolean editRoute(Route route);

    boolean deleteRouteById(int routeId);
}
