package com.example.football_management_system;

public class Person_Info {
    private String person_id;
    private String person_name;
    private String person_fname;
    private String person_lname;
    private String person_country;
    private  String person_mail;
    private int count = 0;

    public Person_Info() {
    }

    public Person_Info(String person_id, String person_name, String person_country) {
        this.person_id = person_id;
        this.person_name = person_name;
        this.person_country = person_country;
    }

    public Person_Info(int a,String person_fname, String person_lname, String person_mail) {
        this.count = a;
        this.person_fname = person_fname;
        this.person_lname = person_lname;
        this.person_mail = person_mail;
    }

    public String getPerson_fname() {
        return person_fname;
    }

    public void setPerson_fname(String person_fname) {
        this.person_fname = person_fname;
    }

    public String getPerson_lname() {
        return person_lname;
    }

    public void setPerson_lname(String person_lname) {
        this.person_lname = person_lname;
    }

    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getPerson_country() {
        return person_country;
    }

    public void setPerson_country(String person_country) {
        this.person_country = person_country;
    }

    public String getPerson_mail() {
        return person_mail;
    }

    public void setPerson_mail(String person_mail) {
        this.person_mail = person_mail;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Person_Info{" +
                "person_id='" + person_id + '\'' +
                ", person_name='" + person_name + '\'' +
                ", person_fname='" + person_fname + '\'' +
                ", person_lname='" + person_lname + '\'' +
                ", person_country='" + person_country + '\'' +
                ", person_mail='" + person_mail + '\'' +
                ", count=" + count +
                '}';
    }
}
