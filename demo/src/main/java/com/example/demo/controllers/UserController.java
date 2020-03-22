package com.example.demo.controllers;

import com.example.demo.databaseAccess.UserDao;
import com.example.demo.entity.Professor;
import com.example.demo.entity.Student;
import com.example.demo.entity.User;
import com.example.demo.factory.UserFactory;
import com.example.demo.factory.UserTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserDao userDao;

    @Autowired
    public UserController(UserDao userDao){ this.userDao=userDao;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    @ResponseBody
    public String createUser(@RequestParam(name = "type",defaultValue = "s") String type,
                             @RequestParam(name = "name") String name,
                             @RequestParam(name = "email") String email,
                             @RequestParam(name = "phone") String phoneNumber,
                             @RequestParam(name = "rn",defaultValue = "-") String resgistrationNumber,
                             @RequestParam(name = "in",defaultValue = "-") String identificationNumber,
                             @RequestParam(name = "dep",defaultValue = "-") String department) {
        User user = null;
        try {

                user = UserFactory.buildUser(UserTypes.getType(type));
                if(user == null)
                    return "Null user!";

            user.setEmail(email);
            user.setName(name);
            user.setPhoneNumber(phoneNumber);

            if(user instanceof Student){
                ((Student) user).setIdentificationNumber(identificationNumber);
                ((Student) user).setRegistrationNumber(resgistrationNumber);
            }
            else if(user instanceof Professor){
                ((Professor) user).setDepartment(department);
            }

            userDao.save(user);
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User succesfully created! (id = " + user.getId() + ")";
    }

}
