package com.example.demo.controllers;

import com.example.demo.databaseAccess.AttendaceCodeDao;
import com.example.demo.databaseAccess.AttendanceDao;
import com.example.demo.databaseAccess.CourseDao;
import com.example.demo.databaseAccess.UserDao;
import com.example.demo.entity.AttendaceCode;
import com.example.demo.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/code")
public class AttendaceCodeController {

    private AttendaceCodeDao codeDao;
    private CourseDao courseDao;

    @Autowired
    public AttendaceCodeController(AttendaceCodeDao attendaceCodeDao,CourseDao courseDao){
        this.courseDao=courseDao;
       this.codeDao = attendaceCodeDao;
    }

    @RequestMapping(value = "/createCode", method = RequestMethod.GET)
    @ResponseBody
    public String  createCode(@RequestParam(name = "course_id") String courseId){
        try {
            Optional<Course> c = courseDao.findById(Long.parseLong(courseId));
            if (!c.isPresent())
                return "Null course";
            AttendaceCode code = new AttendaceCode();
            code.setCourse(c.get());
            codeDao.save(code);
            return "Code for Course: "+code.getCourse().getName()+" is "+code.getCode();
        }catch(Exception ex){
            return ex.getMessage();
        }
    }
}
