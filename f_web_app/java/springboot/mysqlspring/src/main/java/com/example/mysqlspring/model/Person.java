package com.example.mysqlspring.model;

import javax.persistence.*;
import java.util.List;

@MappedSuperclass
public class Person {
    @Id
    @GeneratedValue
    private int id;
    @Embedded
    private Name name;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Address> address;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Phone> phoneList;

    public Person() {
    }

    public Person(Name name, List<Address> address, List<Phone> phoneList) {
        this.name = name;
        this.address = address;
        this.phoneList = phoneList;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name=" + name +
                ", address=" + address +
                ", phoneList=" + phoneList +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public List<Phone> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<Phone> phoneList) {
        this.phoneList = phoneList;
    }
}
