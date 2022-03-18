package com.example.football_management_system;

public class Income {
    String income;
    String bonus;

    public Income() {

    }

    public Income(String income, String bonus) {
        this.income = income;
        this.bonus = bonus;
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

    @Override
    public String toString() {
        return "Income{" +
                "income='" + income + '\'' +
                ", bonus='" + bonus + '\'' +
                '}';
    }
}
