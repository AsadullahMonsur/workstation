package com.example.football_management_system.accessories;

public class Card_Info {
    private String match_id;
    private String player_id;
    private String card_type;
    private String card_time;
    private String patch;

    public Card_Info() {
    }

    public Card_Info(String match_id, String player_id, String card_type, String card_time, String patch) {
        this.match_id = match_id;
        this.player_id = player_id;
        this.card_type = card_type;
        this.card_time = card_time;
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

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public String getCard_time() {
        return card_time;
    }

    public void setCard_time(String card_time) {
        this.card_time = card_time;
    }

    public String getPatch() {
        return patch;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }

    @Override
    public String toString() {
        return "Card_Info{" +
                "match_id='" + match_id + '\'' +
                ", player_id='" + player_id + '\'' +
                ", card_type='" + card_type + '\'' +
                ", card_time='" + card_time + '\'' +
                ", patch='" + patch + '\'' +
                '}';
    }
}
