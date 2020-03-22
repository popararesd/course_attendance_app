package com.example.demo.databaseAccess;

import com.example.demo.entity.AttendaceCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendaceCodeDao extends JpaRepository<AttendaceCode,Long> {
}
