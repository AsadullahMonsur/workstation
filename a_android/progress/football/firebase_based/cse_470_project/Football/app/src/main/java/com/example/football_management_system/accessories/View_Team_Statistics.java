package com.example.football_management_system.accessories;

public class View_Team_Statistics {
    private String match_id = "empty";
    private String list_of_scorer = "empty";
    private String goal_time = "empty";
    private String list_of_got_injured = "empty";
    private String injured_time = "empty";
    private String list_of_got_card = "empty";
    private String card_time = "empty";
    private String card_type = "empty";

    public View_Team_Statistics() {
    }

    public View_Team_Statistics(String match_id, String list_of_scorer, String goal_time, String list_of_got_injured, String injured_time, String list_of_got_card, String card_time, String card_type) {
        this.match_id = match_id;
        this.list_of_scorer = list_of_scorer;
        this.goal_time = goal_time;
        this.list_of_got_injured = list_of_got_injured;
        this.injured_time = injured_time;
        this.list_of_got_card = list_of_got_card;
        this.card_time = card_time;
        this.card_type = card_type;
    }

    public String getMatch_id() {
        return match_id;
    }

    public void setMatch_id(String match_id) {
        this.match_id = match_id;
    }

    public String getList_of_scorer() {
        return list_of_scorer;
    }

    public void setList_of_scorer(String list_of_scorer) {
        this.list_of_scorer = list_of_scorer;
    }

    public String getGoal_time() {
        return goal_time;
    }

    public void setGoal_time(String goal_time) {
        this.goal_time = goal_time;
    }

    public String getList_of_got_injured() {
        return list_of_got_injured;
    }

    public void setList_of_got_injured(String list_of_got_injured) {
        this.list_of_got_injured = list_of_got_injured;
    }

    public String getInjured_time() {
        return injured_time;
    }

    public void setInjured_time(String injured_time) {
        this.injured_time = injured_time;
    }

    public String getList_of_got_card() {
        return list_of_got_card;
    }

    public void setList_of_got_card(String list_of_got_card) {
        this.list_of_got_card = list_of_got_card;
    }

    public String getCard_time() {
        return card_time;
    }

    public void setCard_time(String card_time) {
        this.card_time = card_time;
    }

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    @Override
    public String toString() {
        return "View_Team_Statistics{" +
                "match_id='" + match_id + '\'' +
                ", list_of_scorer='" + list_of_scorer + '\'' +
                ", goal_time='" + goal_time + '\'' +
                ", list_of_got_injured='" + list_of_got_injured + '\'' +
                ", injured_time='" + injured_time + '\'' +
                ", list_of_got_card='" + list_of_got_card + '\'' +
                ", card_time='" + card_time + '\'' +
                ", card_type='" + card_type + '\'' +
                '}';
    }
}
