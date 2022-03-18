package com.example.football_management_system.accessories;

public class Injury_Info {
    private String match_id;
    private String player_id;
    private String injury_time;
    private String patch;

    public Injury_Info() {
    }

    public Injury_Info(String match_id, String player_id, String injury_time, String patch) {
        this.match_id = match_id;
        this.player_id = player_id;
        this.injury_time = injury_time;
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

    public String getInjury_time() {
        return injury_time;
    }

    public void setInjury_time(String injury_time) {
        this.injury_time = injury_time;
    }

    public String getPatch() {
        return patch;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }

    @Override
    public String toString() {
        return "Injury_Info{" +
                "match_id='" + match_id + '\'' +
                ", player_id='" + player_id + '\'' +
                ", injury_time='" + injury_time + '\'' +
                ", patch='" + patch + '\'' +
                '}';
    }
}
