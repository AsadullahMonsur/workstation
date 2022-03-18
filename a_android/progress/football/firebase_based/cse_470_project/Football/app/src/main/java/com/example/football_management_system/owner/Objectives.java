package com.example.football_management_system.owner;

public class Objectives {
    private String id;
    private String objective;
    private String patch;

    public Objectives() {
    }

    public Objectives(String id, String objective, String patch) {
        this.id = id;
        this.objective = objective;
        this.patch = patch;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getPatch() {
        return patch;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }

    @Override
    public String toString() {
        return "Objectives{" +
                "id='" + id + '\'' +
                ", objective='" + objective + '\'' +
                ", patch='" + patch + '\'' +
                '}';
    }
}
