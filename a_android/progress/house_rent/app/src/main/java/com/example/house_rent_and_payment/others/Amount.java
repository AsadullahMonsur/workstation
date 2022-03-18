package com.example.house_rent_and_payment.others;

public class Amount {
    private String currency = "default";
    private int price = 0;

    public Amount() {
    }

    public Amount(String currency, int price) {
        this.currency = currency;
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Amount{" +
                "currency='" + currency + '\'' +
                ", price=" + price +
                '}';
    }
}

