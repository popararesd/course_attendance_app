package com.example.demo.databaseAccess;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Interface used to manage the User table in the database.
 *
 */
public interface UserDao extends JpaRepository<User,Long> {
    public User findByEmail(String email);
}
