package com.example.football_management_system.accessories;

public class User_Info {
    private String id;
    private String name;
    private String f_name;
    private String l_name;
    private String country;
    private  String mail;
    private String password;
    private String age;
    private String position;
    private int flag;
    public User_Info() {
    }

    public User_Info(String id, String name, String country, String password) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.password = password;
    }

    public User_Info(String f_name, String l_name, String mail, String password, int flag) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.mail = mail;
        this.password = password;
        this.flag = flag;
    }

    public User_Info(String id, String name, String country, String password, String age, int flag) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.password = password;
        this.age = age;
        this.flag = flag;
    }

    public User_Info(String id, String name, String country, String password, String age, String position, int flag) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.password = password;
        this.age = age;
        this.position = position;
        this.flag = flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "User_Info{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", f_name='" + f_name + '\'' +
                ", l_name='" + l_name + '\'' +
                ", country='" + country + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", age='" + age + '\'' +
                ", position='" + position + '\'' +
                ", flag=" + flag +
                '}';
    }
}
