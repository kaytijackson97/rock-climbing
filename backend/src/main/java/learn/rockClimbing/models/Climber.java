package learn.rockClimbing.models;

import java.util.List;

public class Climber {
    private int climberId;
    private String name;
    private int age;
    private int monthsClimbing;
    private List<Route> climbs;
    private List<Gym> gyms;

    public int getClimberId() {
        return climberId;
    }

    public void setClimberId(int climberId) {
        this.climberId = climberId;
    }

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
