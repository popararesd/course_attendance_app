package com.example.demo.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * The class used to store information about the courses.
 *
 */
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long courseId;
    @Column
    private String name;
    @Column
    private int credits;
    @ManyToOne
    @JoinColumn(name="profId")
    private Professor professor;

    public Course(){}

    public Course(String name, int credits,Professor professor) {
        this.name = name;
        this.credits = credits;
        this.professor = professor;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Long getId() {
        return courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return getCredits() == course.getCredits() &&
                getName().equals(course.getName()) &&
                getProfessor().equals(course.getProfessor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCredits(), getProfessor());
    }
}
