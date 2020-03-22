package com.example.demo.entity;

import javax.persistence.*;
import java.util.UUID;
/**
 * This class holds the code to be used when trying to submit the attendance.
 */
@Entity
public class AttendaceCode {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "code")
    private String code;

    @OneToOne
    @JoinColumn(name = "course_id")
    private Course Course;

    public AttendaceCode(){
        this.code = UUID.randomUUID().toString();
        this.code.replace("-", "");
        this.code=this.code.substring(0,7);
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode() {
        this.code = UUID.randomUUID().toString();
        this.code.replace("-", "");
        this.code=this.code.substring(0,7);
    }

    public com.example.demo.entity.Course getCourse() {
        return Course;
    }

    public void setCourse(com.example.demo.entity.Course course) {
        Course = course;
    }
}
