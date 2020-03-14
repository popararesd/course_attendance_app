package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String create(String email, String name) {
        Student student = null;
        try {
            student = new Student();
            student.setEmail(email);
            student.setName(name);
            studentDao.save(student);
        }
        catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User succesfully created! (id = " + student.getId() + ")";
    }
}
