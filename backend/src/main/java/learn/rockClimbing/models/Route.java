package learn.rockClimbing.models;

import java.time.LocalDate;

public class Route {
    private RouteType routeType;
    private BoulderingGrade boulderingGrade;
    private RouteGrade routeGrade;
    private int attempts;
    private LocalDate setDate;

    public RouteType getRouteType() {
        return routeType;
    }

    public void setRouteType(RouteType routeType) {
        this.routeType = routeType;
    }

    public BoulderingGrade getBoulderingGrade() {
        return boulderingGrade;
    }

    public void setBoulderingGrade(BoulderingGrade boulderingGrade) {
        this.boulderingGrade = boulderingGrade;
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
