package com.example.football_management_system.accessories;

public class Goal_Info {
    private String match_id;
    private String player_id;
    private String goal_no;
    private String goal_time;
    private String patch;

    public Goal_Info() {
    }

    public Goal_Info(String match_id, String player_id, String goal_no, String goal_time, String patch) {
        this.match_id = match_id;
        this.player_id = player_id;
        this.goal_no = goal_no;
        this.goal_time = goal_time;
        this.patch = patch;
    }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public String getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(String player_id) {
        this.player_id = player_id;
    }

    public String getGoal_no() {
        return goal_no;
    }

    public void setGoal_no(String goal_no) {
        this.goal_no = goal_no;
    }

    public String getGoal_time() {
        return goal_time;
    }

    public void setGoal_time(String goal_time) {
        this.goal_time = goal_time;
    }

    public String getPatch() {
        return patch;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }

    @Override
    public String toString() {
        return "Goal_Info{" +
                "match_id='" + match_id + '\'' +
                ", player_id='" + player_id + '\'' +
                ", goal_no='" + goal_no + '\'' +
                ", goal_time='" + goal_time + '\'' +
                ", patch='" + patch + '\'' +
                '}';
    }
}
