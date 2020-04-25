package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Objects;
/**
 * The class adds a few more specifics of a professor.
 *
 */
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@DiscriminatorValue("professor")
public class Professor extends User {

    @Column
    private String department;

    public Professor(String firstName,String lastName, String email, String phoneNumber, String department) {
        super(firstName,lastName, email, phoneNumber);
        this.department = department;
    }

    public Professor(){}

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Professor)) return false;
        if (!super.equals(o)) return false;
        Professor student = (Professor) o;
        return department.equals(student.department) && student.getEmail().equals(this.getEmail()) &&
                student.getFirstName().equals(this.getFirstName()) && student.getLastName().equals(this.getLastName()) && student.getPhoneNumber().equals(this.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), department);
    }
}
