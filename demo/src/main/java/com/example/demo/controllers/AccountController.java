package com.example.demo.controllers;

import com.example.demo.databaseAccess.AccountDao;
import com.example.demo.databaseAccess.UserDao;
import com.example.demo.entity.Account;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.example.demo.utility.PasswordAuthentication;
import java.util.Optional;

@RestController
@RequestMapping(value = "/account")
public class AccountController {
    private AccountDao accountDao;
    private UserDao userDao;

    @Autowired
    public AccountController (AccountDao accountDao,UserDao userDao){
        this.accountDao=accountDao;
        this.userDao=userDao;
    }

    @RequestMapping(value = "/createAccount", method = RequestMethod.GET)
    @ResponseBody
    public String createAccount(@RequestParam(name = "user_id") String userId,
                                @RequestParam(name = "pass") String pass,
                                @RequestParam(name = "pass_confirm") String passConfirm){
        if(!pass.equals(passConfirm)){
            return "Passwords do not match!";
        }
        User user = null;
        try{
            Optional<User> foundUser= userDao.findById(Long.parseLong(userId));
            if(!foundUser.isPresent())
                return "Null user!";
            String password = (new PasswordAuthentication()).hash(pass);
            user = foundUser.get();
            Account account = new Account();
            account.setUser(user);
            account.setPassword(password);
            accountDao.save(account);
            return "Account successfully created!";
        }catch(Exception ex){
            return ex.getMessage();
        }

    }

    @RequestMapping(value = "/validateLogin", method = RequestMethod.GET)
    @ResponseBody
    public String validateLogin(@RequestParam(name = "email") String email,
                                @RequestParam(name = "pass") String pass){
        User user =null;
        Account account = null;
        try{
            Optional<User> foundUser = Optional.ofNullable(userDao.findByEmail(email));
            if(!foundUser.isPresent())
                return "User inexistent!";
            user = foundUser.get();
            Account foundAccount = accountDao.findByUser(user);
            if((new PasswordAuthentication()).authenticate(pass,foundAccount.getPassword()))
                return "Valid!";
            else
                return "Invalid!";

        }catch(Exception ex){
            return ex.getMessage();
        }
    }

}
