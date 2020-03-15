package com.example.demo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Lecture {

    public List<Student> attendees;
    public String lectureTitle;
    public String lectureRoomNumber;
    public Date lectureDate;

    public Date getLectureDate() {
        return lectureDate;
    }

    public void setLectureDate(Date lectureDate) {
        this.lectureDate = lectureDate;
    }

    public Lecture (String lectureTitle, String lectureRoomNumber){
        this.attendees = new ArrayList<>();
        this.lectureTitle=lectureTitle;
        this.lectureRoomNumber=lectureRoomNumber;
    }

    public List<Student> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<Student> attendees) {
        this.attendees = attendees;
    }

    public String getLectureTitle() {
        return lectureTitle;
    }

    public void setLectureTitle(String lectureTitle) {
        this.lectureTitle = lectureTitle;
    }

    public String getLectureRoomNumber() {
        return lectureRoomNumber;
    }

    public void setLectureRoomNumber(String lectureRoomNumber) {
        this.lectureRoomNumber = lectureRoomNumber;
    }

    public void markAttendance(Student student){
        this.attendees.add(student);
    }
}
