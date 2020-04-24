package com.example.demo.factory;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Professor;
import com.example.demo.entity.Student;
import com.example.demo.entity.User;
/**
 * The class used to build Users.
 *
 */
public class UserFactory {
    /**
     * Creates a new User based ont the parameter userType.
     * Expects a value from the UserTypes Enum
     * @param  userType a value from the enum UserTypes coresponding with the User
     * @return      a new User coresponing with the param
     * @see         User
     */
    public static User buildUser(UserTypes userType){
        User user = null;
        if(userType == UserTypes.STUDENT)
            user = new Student();
        else if(userType == UserTypes.PROFESSOR)
            user = new Professor();
        else if(userType == UserTypes.ADMIN)
            user = new Admin();
        return user;
    }
}
