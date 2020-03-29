package com.example.demo.databaseAccess;

import com.example.demo.entity.Account;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDao extends JpaRepository<Account,Long> {
    public Account findByUser(User user);
}
