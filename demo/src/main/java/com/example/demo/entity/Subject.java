package com.example.demo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column
    private int credits;
    @Column
    private String name;
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "subject_students",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<User> enrolledStudents = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "porf_id")
    private Professor professor;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "courseId")
    private List<Course> courses = new ArrayList<>();

    public void addObserver(StudentObserver user){
        this.enrolledStudents.add((User)user);
    }

    public void removeObserver(StudentObserver user){
        this.enrolledStudents.remove((User)user);
    }
    public void addNewCourse(Course course,String message){
        this.courses.add(course);
        for(StudentObserver s : this.enrolledStudents)
            s.update(message);
    }

    public Long getId() {
        return id;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void setEnrolledStudents(List<User> enrolledStudents) {
        this.enrolledStudents = enrolledStudents;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course){
        this.courses.add(course);
    }
}
