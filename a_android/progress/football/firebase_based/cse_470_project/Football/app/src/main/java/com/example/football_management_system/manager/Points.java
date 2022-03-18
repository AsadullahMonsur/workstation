package com.example.football_management_system.manager;

public class Points {
    private String points;
    private String id;
    private String patch;

    public Points() {
    }

    public Points(String points, String id, String patch) {
        this.points = points;
        this.id = id;
        this.patch = patch;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatch() {
        return patch;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }

    @Override
    public String toString() {
        return "Points{" +
                "points='" + points + '\'' +
                ", id='" + id + '\'' +
                ", patch='" + patch + '\'' +
                '}';
    }
}
