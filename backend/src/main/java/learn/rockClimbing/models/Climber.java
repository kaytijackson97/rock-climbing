package learn.rockClimbing.models;

import java.util.List;

public class Climber {
    private String name;
    private int age;
    private int monthsClimbing;
    private BoulderingGrade hardestBoulder;
    private RouteGrade hardestTopRope;
    private RouteGrade hardestLead;
    private List<Route> climbs;
    private List<Gym> gyms;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMonthsClimbing() {
        return monthsClimbing;
    }

    public void setMonthsClimbing(int monthsClimbing) {
        this.monthsClimbing = monthsClimbing;
    }

    public BoulderingGrade getHardestBoulder() {
        return hardestBoulder;
    }

    public void setHardestBoulder(BoulderingGrade hardestBoulder) {
        this.hardestBoulder = hardestBoulder;
    }

    public RouteGrade getHardestTopRope() {
        return hardestTopRope;
    }

    public void setHardestTopRope(RouteGrade hardestTopRope) {
        this.hardestTopRope = hardestTopRope;
    }

    public RouteGrade getHardestLead() {
        return hardestLead;
    }

    public void setHardestLead(RouteGrade hardestLead) {
        this.hardestLead = hardestLead;
    }

    public List<Route> getClimbs() {
        return climbs;
    }

    public void setClimbs(List<Route> climbs) {
        this.climbs = climbs;
    }

    public List<Gym> getGyms() {
        return gyms;
    }

    public void setGyms(List<Gym> gyms) {
        this.gyms = gyms;
    }
}
