package com.example.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("student")
public class Student extends User {

    @Column
    private String registrationNumber;
    @Column
    private String identificationNumber;

    public Student (String name, String email, String phoneNumber,String registrationNumber,String identificationNumber){
        super(name,email,phoneNumber);
        this.registrationNumber=registrationNumber;
        this.identificationNumber=identificationNumber;
    }

    public Student() {}

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

}
