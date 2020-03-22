package com.example.demo.controllers;

import com.example.demo.databaseAccess.CourseDao;
import com.example.demo.databaseAccess.UserDao;
import com.example.demo.entity.Course;
import com.example.demo.entity.Professor;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/course")
public class CourseController {
    private CourseDao courseDao;
    private UserDao userDao;

    @Autowired
    public CourseController(CourseDao courseDao,UserDao userDao){
        this.courseDao=courseDao;
        this.userDao=userDao;
    }

    @RequestMapping(value = "/addCourse", method = RequestMethod.GET)
    @ResponseBody
    public String addCourse(@RequestParam(name = "name") String name,
                            @RequestParam(name = "cr") String credits,
                            @RequestParam(name = "pr") String profId) {
        Course course = null;
        try {

            course = new Course();
            if (course == null)
                return "Null user!";
            course.setName(name);
            course.setCredits(Integer.parseInt(credits));
            Optional<User> p = userDao.findById(Long.parseLong(profId));
            User prof = p.get();
            if (!(prof instanceof Professor))
                return "Not prof";
            course.setProfessor((Professor) prof);
            courseDao.save(course);
        } catch (Exception ex) {
            return ex.getMessage();
        }
        return "Course added succesfully";
    }
}
