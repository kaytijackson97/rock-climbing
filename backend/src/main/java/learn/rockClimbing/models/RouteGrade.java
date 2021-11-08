package learn.rockClimbing.models;

public enum RouteGrade {
    FIVE_INTRO("5.Intro"),
    FIVE_FOUR("5.4"),
    FIVE_FIVE("5.5"),
    FIVE_SIX("5.6"),
    FIVE_SEVEN("5.7"),
    FIVE_EIGHT("5.8"),
    FIVE_NINE("5.9"),
    FIVE_TEN_A("5.10a"),
    FIVE_TEN_B("5.10b"),
    FIVE_TEN_C("5.10c"),
    FIVE_TEN_D("5.10d"),
    FIVE_ELEVEN_A("5.11a"),
    FIVE_ELEVEN_B("5.11b"),
    FIVE_ELEVEN_C("5.11c"),
    FIVE_ELEVEN_D("5.11d"),
    FIVE_TWELVE_A("5.12a"),
    FIVE_TWELVE_B("5.12b"),
    FIVE_TWELVE_C("5.12c"),
    FIVE_TWELVE_D("5.12d"),
    FIVE_THIRTEEN_A("5.13a"),
    FIVE_THIRTEEN_B("5.13b"),
    FIVE_THIRTEEN_C("5.13c"),
    FIVE_THIRTEEN_D("5.13d"),
    FIVE_FOURTEEN_A("5.14a"),
    FIVE_FOURTEEN_B("5.14b"),
    FIVE_FOURTEEN_C("5.14c"),
    FIVE_FOURTEEN_D("5.14d"),
    FIVE_FIFTEEN_A("5.15a"),
    FIVE_FIFTEEN_B("5.15b"),
    FIVE_FIFTEEN_C("5.15c");

    private final String routeGrade;

    RouteGrade(String routeGrade) {
        this.routeGrade = routeGrade;
    }

    public String getRouteGrade() {
        return routeGrade;
    }
}
