package com.example.silkwaytransit.network.user;

public class Users {
    private String FullName;
    private String Email;
    private String Role;
    private String Id;

    public Users(){}

    public Users(String fullName, String email, String role, String id){
        this.FullName = fullName;
        this.Email = email;
        this.Role = role;
        this.Id = id;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    @Override
    public String toString() {
        return "Drivers{" +
                "FullName='" + FullName + '\'' +
                ", Email=" + Email +
                ", Role=" + Role + '}';
    }
}