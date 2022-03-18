package com.example.football_management_system;

public class Money_And_Honey {
    private String objective;
    private String owner_cont2_manager_id;

    private String owner_cont3_manager_id;
    private String owner_cont3_manager_income;
    private String owner_cont3_manager_bonus;

    private String owner_cont3_player_id;
    private String owner_cont3_player_income;
    private String owner_cont3_player_bonus;

    private String manager_cont3_points;
    private String manager_cont3_player_id;

    public Money_And_Honey() {
    }

    public Money_And_Honey(String objective, String owner_cont2_manager_id) {
        this.objective = objective;
        this.owner_cont2_manager_id = owner_cont2_manager_id;
    }

    public Money_And_Honey(String owner_cont3_manager_id, String owner_cont3_manager_income, String owner_cont3_manager_bonus) {
        this.owner_cont3_manager_id = owner_cont3_manager_id;
        this.owner_cont3_manager_income = owner_cont3_manager_income;
        this.owner_cont3_manager_bonus = owner_cont3_manager_bonus;
    }

    public Money_And_Honey(int flag, String owner_cont3_player_id, String owner_cont3_player_income, String owner_cont3_player_bonus) {
        this.owner_cont3_player_id = owner_cont3_player_id;
        this.owner_cont3_player_income = owner_cont3_player_income;
        this.owner_cont3_player_bonus = owner_cont3_player_bonus;
    }

    public Money_And_Honey(int flag1, int flag2, int flag3, String manager_cont3_points, String manager_cont3_player_id) {
        this.manager_cont3_points = manager_cont3_points;
        this.manager_cont3_player_id = manager_cont3_player_id;
    }

    @Override
    public String toString() {
        return "Money_And_Honey{" +
                "objective='" + objective + '\'' +
                ", owner_cont2_manager_id='" + owner_cont2_manager_id + '\'' +
                ", owner_cont3_manager_id='" + owner_cont3_manager_id + '\'' +
                ", owner_cont3_manager_income='" + owner_cont3_manager_income + '\'' +
                ", owner_cont3_manager_bonus='" + owner_cont3_manager_bonus + '\'' +
                ", owner_cont3_player_id='" + owner_cont3_player_id + '\'' +
                ", owner_cont3_player_income='" + owner_cont3_player_income + '\'' +
                ", owner_cont3_player_bonus='" + owner_cont3_player_bonus + '\'' +
                ", manager_cont3_points='" + manager_cont3_points + '\'' +
                ", manager_cont3_player_id='" + manager_cont3_player_id + '\'' +
                '}';
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getOwner_cont2_manager_id() {
        return owner_cont2_manager_id;
    }

    public void setOwner_cont2_manager_id(String owner_cont2_manager_id) {
        this.owner_cont2_manager_id = owner_cont2_manager_id;
    }

    public String getOwner_cont3_manager_id() {
        return owner_cont3_manager_id;
    }

    public void setOwner_cont3_manager_id(String owner_cont3_manager_id) {
        this.owner_cont3_manager_id = owner_cont3_manager_id;
    }

    public String getOwner_cont3_manager_income() {
        return owner_cont3_manager_income;
    }

    public void setOwner_cont3_manager_income(String owner_cont3_manager_income) {
        this.owner_cont3_manager_income = owner_cont3_manager_income;
    }

    public String getOwner_cont3_manager_bonus() {
        return owner_cont3_manager_bonus;
    }

    public void setOwner_cont3_manager_bonus(String owner_cont3_manager_bonus) {
        this.owner_cont3_manager_bonus = owner_cont3_manager_bonus;
    }

    public String getOwner_cont3_player_id() {
        return owner_cont3_player_id;
    }

    public void setOwner_cont3_player_id(String owner_cont3_player_id) {
        this.owner_cont3_player_id = owner_cont3_player_id;
    }

    public String getOwner_cont3_player_income() {
        return owner_cont3_player_income;
    }

    public void setOwner_cont3_player_income(String owner_cont3_player_income) {
        this.owner_cont3_player_income = owner_cont3_player_income;
    }

    public String getOwner_cont3_player_bonus() {
        return owner_cont3_player_bonus;
    }

    public void setOwner_cont3_player_bonus(String owner_cont3_player_bonus) {
        this.owner_cont3_player_bonus = owner_cont3_player_bonus;
    }

    public String getManager_cont3_points() {
        return manager_cont3_points;
    }

    public void setManager_cont3_points(String manager_cont3_points) {
        this.manager_cont3_points = manager_cont3_points;
    }

    public String getManager_cont3_player_id() {
        return manager_cont3_player_id;
    }

    public void setManager_cont3_player_id(String manager_cont3_player_id) {
        this.manager_cont3_player_id = manager_cont3_player_id;
    }
}
