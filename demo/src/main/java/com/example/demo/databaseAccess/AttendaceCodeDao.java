package com.example.demo.databaseAccess;

import com.example.demo.entity.AttendaceCode;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Interface used to manage the AttendaceCode table in the database.
 *
 */
public interface AttendaceCodeDao extends JpaRepository<AttendaceCode,Long> {
}
