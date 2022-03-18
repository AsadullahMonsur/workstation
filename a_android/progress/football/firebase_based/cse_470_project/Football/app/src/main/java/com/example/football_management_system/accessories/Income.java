package com.example.football_management_system.accessories;

public class Income {
    private String id;
    private String income;
    private String bonus;
    private String patch;

    public Income() {

    }

    public Income(String id, String income, String bonus, String patch) {
        this.id = id;
        this.income = income;
        this.bonus = bonus;
        this.patch = patch;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public String getPatch() {
        return patch;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }

    @Override
    public String toString() {
        return "Income{" +
                "id='" + id + '\'' +
                ", income='" + income + '\'' +
                ", bonus='" + bonus + '\'' +
                ", patch='" + patch + '\'' +
                '}';
    }
}
