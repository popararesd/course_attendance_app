package com.example.demo.controllers;

import com.example.demo.databaseAccess.StudentDao;
import com.example.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StudentController {

    @Autowired
    private StudentDao studentDao;
    /*
    @param email User's email
            * @param name User's name
            * @return A string describing if the user is successfully created or not.
   */
    @RequestMapping("/create")
    @ResponseBody
    public String create(@RequestParam("name") String name,
                         @RequestParam("email") String email,
                         @RequestParam("phone") String phoneNumber,
                         @RequestParam("in") String identificationNumber,
                         @RequestParam("rn") String registrationNumber) {
        Student student = null;
        try {
            student = new Student();
            student.setEmail(email);
            student.setName(name);
            student.setIdentificationNumber(identificationNumber);
            student.setPhoneNumber(phoneNumber);
            student.setRegistrationNumber(registrationNumber);
            studentDao.save(student);
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User succesfully created! (id = " + student.getId() + ")";
    }

}