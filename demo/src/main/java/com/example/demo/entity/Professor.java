package com.example.demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("professor")
public class Professor extends User {

    @Column
    private String department;

    public Professor(String name, String email, String phoneNumber, String department) {
        super(name, email, phoneNumber);
        this.department = department;
    }

    public Professor(){}

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
