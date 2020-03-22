package com.example.demo.databaseAccess;

import com.example.demo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Interface used to manage the Course table in the database.
 *
 */
public interface CourseDao extends JpaRepository<Course,Long> {
    public Course findByName(String name);
}
