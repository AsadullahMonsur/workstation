package com.example.mysqlspring.model;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Salesman extends Person{

    private double salary;
    private double totalSale;
    private LocalDate startDate;

    public Salesman() {
    }

    public Salesman(double salary, double totalSale, LocalDate startDate) {
        this.salary = salary;
        this.totalSale = totalSale;
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Salesman{" +
                "salary=" + salary +
                ", totalSale=" + totalSale +
                ", startDate=" + startDate +
                '}';
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getTotalSale() {
        return totalSale;
    }

    public void setTotalSale(double totalSale) {
        this.totalSale = totalSale;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
