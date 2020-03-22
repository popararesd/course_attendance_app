package com.example.demo.entity;

import javax.persistence.*;
import java.util.*;

@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long attendanceId;

    @ManyToOne
    @JoinColumn(name = "studId")
    private Student student;
    @ManyToOne
    @JoinColumn(name="courseId")
    private Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

}
