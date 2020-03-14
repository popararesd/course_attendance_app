package com.example.demo.entity;

import javax.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String phoneNumber;
    @Column
    private String registrationNumber;
    @Column
    private String identificationNumber;

    public Student (String name, String email, String phoneNumber,String registrationNumber,String identificationNumber){
        this.name=name;
        this.email=email;
        this.phoneNumber=phoneNumber;
        this.registrationNumber=registrationNumber;
        this.identificationNumber=identificationNumber;
    }

    public Student() {

    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId(){return this.id;}
}
