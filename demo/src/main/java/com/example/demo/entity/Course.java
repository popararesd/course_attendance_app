package com.example.demo.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
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
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<User> attendace;
    @Column
    private Date date;

    public Course(){}

    public Course(String name) {
        this.name = name;
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

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public List<User> getAttendace() {
        return attendace;
    }

    public void setAttendace(List<User> attendace) {
        this.attendace = attendace;
    }

    public void markAttendance(Student student){
        this.attendace.add(student);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return getName().equals(course.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
