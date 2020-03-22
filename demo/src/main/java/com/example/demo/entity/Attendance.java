package com.example.demo.entity;

import javax.persistence.*;
import java.util.*;
/**
 * The class is used to store information about the students that particiapted int he course.
 *
 */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Attendance)) return false;
        Attendance that = (Attendance) o;
        return getStudent().equals(that.getStudent()) &&
                getCourse().equals(that.getCourse());
    }

    public Long getAttendanceId() {
        return attendanceId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudent(), getCourse());
    }
}
