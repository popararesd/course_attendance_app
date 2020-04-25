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
     * Inserts a new course into the database.
     * @param name The name of the course.
     * @param day The day the course is held.
     * @param mon The month the course is held.
     * @param year The year the course is held.
     * @return The course object.
     */
    @RequestMapping(value = "/addCourse", method = RequestMethod.POST)
    @ResponseBody
    public Course addCourse(@RequestParam(name = "name") String name,
                            @RequestParam(name = "day") String day,
                            @RequestParam(name = "mon") String mon,
                            @RequestParam(name = "year") String year) {
        Course course = null;
        try {
            course = new Course();
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
            course.setName(name);
            courseDao.save(course);
        } catch (Exception ex) {
            return null;
        }
        return course;
    }

    /**
     * Inserts the student into the atendee list.
     * @param courseId The id of the course.
     * @param studentId The id of the student.
     * @return The updated course.
     */
    @RequestMapping(value = "/markAttendance", method = RequestMethod.PUT)
    @ResponseBody
    public Course markAttendance(@RequestParam(name = "c_id") String courseId,
                                 @RequestParam(name = "s_id") String studentId){
        Student student = null;
        Course course = null;
        try {
            Long cId = Long.parseLong(courseId);
            Long sId = Long.parseLong(studentId);
            Optional<Course> foundCourse = courseDao.findById(cId);
            if(!foundCourse.isPresent())
                return null;
            Optional<User> foundUser = userDao.findById(sId);
            if(!foundUser.isPresent())
                return null;
            student = (Student) foundUser.get();
            course = foundCourse.get();
            course.markAttendance(student);
            courseDao.save(course);
        } catch (Exception ex) {
            return null;
        }
        return course;

    }

    @RequestMapping(value = "/getCourse", method = RequestMethod.GET)
    @ResponseBody
    public Course getCourse(@RequestParam(name = "c_id") String courseId){
        Course course = null;
        try {
            Long cId = Long.parseLong(courseId);
            return courseDao.findById(cId).get();
        } catch (Exception ex) {
            return null;
        }
    }


}
