package com.example.demo.databaseAccess;

import com.example.demo.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectDao extends JpaRepository<Subject,Long> {
}
