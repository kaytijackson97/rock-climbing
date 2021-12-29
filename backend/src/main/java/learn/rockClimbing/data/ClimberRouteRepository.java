package learn.rockClimbing.data;

import learn.rockClimbing.models.ClimberRoute;

public interface ClimberRouteRepository {
    boolean add(ClimberRoute climberRoute);

    boolean deleteByKey(int climberId, int routeId);
}
