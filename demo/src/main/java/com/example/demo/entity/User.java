package com.example.demo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The basic entity of the application.
 * This class contains the basic information of all the User types in the application.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User implements StudentObserver {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private Long id;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String phoneNumber;
    @ManyToMany(mappedBy = "enrolledStudents")
    private List<Subject> enrolledSubjects = new ArrayList<>();

    @Column
    private String newCourse;

    public User (String name, String email, String phoneNumber){
        this.name=name;
        this.email=email;
        this.phoneNumber=phoneNumber;
    }

    public User (){}

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

    public Long getId(){return this.id;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return name.equals(user.name) &&
                email.equals(user.email) &&
                phoneNumber.equals(user.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, phoneNumber);
    }

    public String getNewCourse(){
        return this.newCourse;
    }

    @Override
    public void update(String newCourse) {
        this.newCourse = newCourse;
    }
}
