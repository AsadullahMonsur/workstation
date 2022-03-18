package com.example.football_management_system.accessories;

public class Goal {
    private String player_id;
    private int goal;
    private String patch;

    public Goal() {
    }

    public Goal(String player_id, int goal, String patch) {
        this.player_id = player_id;
        this.goal = goal;
        this.patch = patch;
    }

    public String getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(String player_id) {
        this.player_id = player_id;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public String getPatch() {
        return patch;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }

    @Override
    public String toString() {
        return "Goal{" +
                "player_id='" + player_id + '\'' +
                ", goal=" + goal +
                ", patch='" + patch + '\'' +
                '}';
    }
}
