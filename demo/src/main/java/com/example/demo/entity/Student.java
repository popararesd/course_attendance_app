package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The class adds a few more specifics of a student.
 *
 */
@Entity
@DiscriminatorValue("student")
public class Student extends User {

    @Column
    private String registrationNumber;
    @Column
    private String identificationNumber;

    @JsonBackReference
    @ManyToMany(mappedBy = "enrolledStudents")
    @JsonIgnoreProperties("enrolledStudents")
    private List<Subject> enrolledSubjects = new ArrayList<>();

    @JsonBackReference
    @ManyToMany(mappedBy = "attendace")
    @JsonIgnoreProperties("attendace")
    private List<Course> attendedCourses = new ArrayList<>();

    public List<Subject> getEnrolledSubjects() {
        return enrolledSubjects;
    }

    public void setEnrolledSubjects(List<Subject> enrolledSubjects) {
        this.enrolledSubjects = enrolledSubjects;
    }

    public Student (String firstName,String lastName, String email, String phoneNumber,String registrationNumber,String identificationNumber){
        super(firstName,lastName,email,phoneNumber);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(registrationNumber, student.registrationNumber) &&
                Objects.equals(identificationNumber, student.identificationNumber) && student.getEmail().equals(this.getEmail()) &&
                student.getFirstName().equals(this.getFirstName()) && student.getLastName().equals(this.getLastName()) && student.getPhoneNumber().equals(this.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber, identificationNumber);
    }

    public List<Course> getAttendedCourses() {
        return attendedCourses;
    }

    public void setAttendedCourses(List<Course> attendedCourses) {
        this.attendedCourses = attendedCourses;
    }
}
