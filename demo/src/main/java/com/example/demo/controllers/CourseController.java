package com.example.demo.controllers;

import com.example.demo.databaseAccess.CourseDao;
import com.example.demo.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/course")
public class CourseController {

    private CourseDao courseDao;

    @Autowired
    public CourseController(CourseDao courseDao){
        this.courseDao=courseDao;
    }

    @RequestMapping(value = "/createCourse", method = RequestMethod.GET)
    @ResponseBody
    public String createCourse(@RequestParam(name = "name") String name,
                             @RequestParam(name = "credits") String credits,
                             @RequestParam(name = "year") String yearOfStudy,
                             @RequestParam(name = "fac") String faculty){
        Course course = null;
        try {
            course = new Course();
            course.setName(name);
            course.setCredits(Integer.parseInt(credits));
            course.setYearOfStudy(Integer.parseInt(yearOfStudy));
            course.setFaculty(faculty);
            course.setEnrolledStudents(null);
            course.setLectures(null);
            course.setProfessor(null);
            courseDao.save(course);

        }catch(Exception ex){
            return "Error creating the course: " + ex.toString();
        }
        return "Course succesfully created! (id = " + course.getId() + ")";
    }

}
