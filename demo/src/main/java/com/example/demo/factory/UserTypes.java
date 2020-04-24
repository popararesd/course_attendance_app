package com.example.demo.factory;

/**
 * Enumeration holding the user types.
 */
public enum UserTypes {
    STUDENT,PROFESSOR,ADMIN,ERROR;
    public static UserTypes getType(String type){
        if(type.equals("s"))
            return STUDENT;
        else if(type.equals("p"))
            return PROFESSOR;
        else if(type.equals("a"))
            return ADMIN;
        else
            return ERROR;
    }
}
