package com.example.demo.factory;

import com.example.demo.entity.*;
import com.example.demo.factory.UserTypes;

public class UserFactory {
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
