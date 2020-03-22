package com.example.demo.databaseAccess;

import com.example.demo.entity.Attendance;
import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceDao extends JpaRepository<Attendance,Long> {
    public Attendance findByCourse(Course course);
}
