package com.example.mysqlspring.model;

import com.example.mysqlspring.repository.CustomerRepo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Customer extends Person{

    private double openingBalance;
    private double balance;
    private  double totalMarket;
    private double totalPaid;
    private double totalDue;
    private String startingDate;

    public Customer() {
    }

    public Customer(Name name, List<Address> address, List<Phone> pList, double openingBalance, double totalMarket, double totalPaid, String startingDate) {
        super(name,address,pList);
        this.openingBalance = openingBalance;
        this.balance = totalPaid+openingBalance-totalMarket;
        this.totalMarket = totalMarket;
        this.totalPaid = totalPaid;
        this.totalDue = totalMarket-openingBalance+totalPaid;;
        this.startingDate = startingDate;
    }

    @Override
    public String toString() {
        return "Customer{" +
                ", openingBalance=" + openingBalance +
                ", balance=" + balance +
                ", totalMarket=" + totalMarket +
                ", totalPaid=" + totalPaid +
                ", totalDue=" + totalDue +
                ", startingDate=" + startingDate +
                '}';
    }


    public double getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(double openingBalance) {
        this.openingBalance = openingBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getTotalMarket() {
        return totalMarket;
    }

    public void setTotalMarket(double totalMarket) {
        this.totalMarket = totalMarket;
    }

    public double getTotalPaid() {
        return totalPaid;
    }

    public void setTotalPaid(double totalPaid) {
        this.totalPaid = totalPaid;
    }

    public double getTotalDue() {
        return totalDue;
    }

    public void setTotalDue(double totalDue) {
        this.totalDue = totalDue;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }
}
