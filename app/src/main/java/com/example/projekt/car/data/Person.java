package com.example.projekt.car.data;

import java.io.Serializable;

//User info: login, email, password, id, lastUsedCar
public class Person implements Serializable {
    private String firstName;
    private String secondName;
    private String email;           //email is also ID
    private String password;
    private String lastUsedCar;
    private String token;
    private String role;

    public Person(String firstName, String secondName, String email, String password, String role) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public String getLastUsedCar() {
        return lastUsedCar;
    }

    public void setLastUsedCar(String lastUsedCar) {
        this.lastUsedCar = lastUsedCar;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", lastUsedCar='" + lastUsedCar + '\'' +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getPassword() {

        return password;
    }
}
