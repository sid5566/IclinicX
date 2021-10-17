package com.example.education;

public class inputModel {
    String Name;
    String Email;
    String Password;
    String Uid;

    public inputModel() {
    }

    public inputModel(String name, String email, String password, String uid) {
        Name = name;
        Email = email;
        Password = password;
        Uid = uid;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }
}
