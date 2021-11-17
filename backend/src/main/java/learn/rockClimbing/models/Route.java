package learn.rockClimbing.models;

import java.time.LocalDate;

public class Route {
    private int routeId;
    private Gym gym;
    private RouteType routeType;
    private RouteGrade routeGrade;
    private int attempts;
    private LocalDate setDate;

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    public RouteType getRouteType() {
        return routeType;
    }

    public void setRouteType(RouteType routeType) {
        this.routeType = routeType;
    }

    public RouteGrade getRouteGrade() {
        return routeGrade;
    }

    public void setRouteGrade(RouteGrade routeGrade) {
        this.routeGrade = routeGrade;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public LocalDate getSetDate() {
        return setDate;
    }

    public void setSetDate(LocalDate setDate) {
        this.setDate = setDate;
    }
}
