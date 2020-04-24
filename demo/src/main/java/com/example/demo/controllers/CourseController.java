package com.example.demo.controllers;

import com.example.demo.databaseAccess.CourseDao;
import com.example.demo.databaseAccess.UserDao;
import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    /**
     * Inserts a new Course into the database.
     * @param  name The name for the course
     * @param credits The number of credits for the course
     * @param profId The id of the prof teaching the course
     * @return      a String for success of failure
     * @see         Course
     */
    @RequestMapping(value = "/addCourse", method = RequestMethod.GET)
    @ResponseBody
    public String addCourse(@RequestParam(name = "name") String name,
                            @RequestParam(name = "day") String day,
                            @RequestParam(name = "mon") String mon,
                            @RequestParam(name = "year") String year) {
        Course course = null;
        try {

            course = new Course();
            course.setName(name);
            int dayInt = Integer.parseInt(day);
            int monInt = Integer.parseInt(mon);
            int yearInt = Integer.parseInt(year);
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR,yearInt);
            cal.set(Calendar.MONTH,monInt);
            cal.set(Calendar.DAY_OF_MONTH,dayInt);
            Date date = cal.getTime();
            course.setDate(date);
            course.setAttendace(new ArrayList<>());
            courseDao.save(course);
        } catch (Exception ex) {
            return ex.getMessage();
        }
        return "Course added succesfully!";
    }

    @RequestMapping(value = "/markAttendance", method = RequestMethod.GET)
    @ResponseBody
    public String markAttendance(@RequestParam(name = "c_id") String courseId,
                                 @RequestParam(name = "s_id") String studentId){
        Student student = null;
        Course course = null;
        try {
            Long cId = Long.parseLong(courseId);
            Long sId = Long.parseLong(studentId);
            Optional<Course> foundCourse = courseDao.findById(cId);
            if(!foundCourse.isPresent())
                return "Null course!";
            Optional<User> foundUser = userDao.findById(sId);
            if(!foundUser.isPresent())
                return "Null user!";
            student = (Student) foundUser.get();
            course = foundCourse.get();
            course.markAttendance(student);
            courseDao.save(course);
        } catch (Exception ex) {
            return ex.getMessage();
        }
        return "Successfully marked attendance for "+ student.getFirstName()+ " " + student.getLastName() +"!";

    }


}
