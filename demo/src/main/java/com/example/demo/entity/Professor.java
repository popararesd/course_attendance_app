package com.example.demo.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
/**
 * The class adds a few more specifics of a professor.
 *
 */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Professor)) return false;
        if (!super.equals(o)) return false;
        Professor student = (Professor) o;
        return department.equals(student.department) && student.getEmail().equals(this.getEmail()) &&
                student.getName().equals(this.getName()) && student.getPhoneNumber().equals(this.getPhoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), department);
    }
}
