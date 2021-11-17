package learn.rockClimbing.models;

public enum GradingSystem {
    BOULDERING("Bouldering"),
    YOSEMITE("Yosemite");

    private final String gradingSystem;

    GradingSystem(String gradingSystem) {
        this.gradingSystem = gradingSystem;
    }

    public String getGradingSystem() {
        return gradingSystem;
    }
}
