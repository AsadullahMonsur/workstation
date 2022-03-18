package com.example.football_management_system.accessories;

public class Match_Info {
    private String match_id;
    private String match_venue;
    private String patch;

    public Match_Info() {
    }

    public Match_Info(String match_id, String match_venue, String patch) {
        this.match_id = match_id;
        this.match_venue = match_venue;
        this.patch = patch;
    }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public String getMatch_venue() {
        return match_venue;
    }

    public void setMatch_venue(String match_venue) {
        this.match_venue = match_venue;
    }

    public String getPatch() {
        return patch;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }

    @Override
    public String toString() {
        return "Match_Info{" +
                "match_id='" + match_id + '\'' +
                ", match_venue='" + match_venue + '\'' +
                ", patch='" + patch + '\'' +
                '}';
    }
}
