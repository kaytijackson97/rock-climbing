package learn.rockClimbing.models;

public class RouteGrade {
    private int routeGradeId;
    private String gradingSystem;
    private String gradeLevel;

    public int getRouteGradeId() {
        return routeGradeId;
    }

    public void setRouteGradeId(int routeGradeId) {
        this.routeGradeId = routeGradeId;
    }

    public String getGradingSystem() {
        return gradingSystem;
    }

    public void setGradingSystem(String gradingSystem) {
        this.gradingSystem = gradingSystem;
    }

    public String getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }
}
