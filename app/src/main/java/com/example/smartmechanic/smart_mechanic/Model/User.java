package com.example.smartmechanic.smart_mechanic.Model;

public class User {

    private String Username;
    private String Password;
    private String Name;
    private String Address;
    private String Phone;

    public User() {

    }

    public User(String username, String password, String name, String address) {
        Username = username;
        Password = password;
        Name = name;
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
