package learn.rockClimbing.models;

public enum RouteType {
    BOULDERING("Bouldering"),
    TOP_ROPE("Top Rope"),
    LEAD("Lead");

    private final String route;

    RouteType(String route) {
        this.route = route;
    }

    public String getRoutes() {
        return route;
    }
}
