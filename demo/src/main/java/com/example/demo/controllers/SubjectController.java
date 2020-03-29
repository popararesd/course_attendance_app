package com.example.demo.controllers;

import com.example.demo.databaseAccess.CourseDao;
import com.example.demo.databaseAccess.SubjectDao;
import com.example.demo.databaseAccess.UserDao;
import com.example.demo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/subject")
public class SubjectController {

    private SubjectDao subjectDao;
    private CourseDao courseDao;
    private UserDao userDao;

    @Autowired
    public SubjectController(SubjectDao subjectDao,CourseDao courseDao,UserDao userDao){
        this.subjectDao=subjectDao;
        this.courseDao=courseDao;
        this.userDao=userDao;
    }

    @RequestMapping(value = "/addSubject", method = RequestMethod.GET)
    @ResponseBody
    public String addSubject(@RequestParam(name = "name") String name,
                            @RequestParam(name = "cr") String credits,
                            @RequestParam(name = "prof_id") String professorId){
        Subject subject = null;
        Professor professor = null;
        try{
            subject = new Subject();
            Optional<User> foundUser = userDao.findById(Long.parseLong(professorId));
            if(!foundUser.isPresent()){
                return "Null proffessor!";
            }
            professor = (Professor)foundUser.get();
            subject.setProfessor(professor);
            subject.setCredits(Integer.parseInt(credits));
            subject.setName(name);
            subjectDao.save(subject);
            return "Successfully added subject!";
        }catch(Exception ex){
            return ex.getMessage();
        }
    }

    @RequestMapping(value = "/addCourse", method = RequestMethod.GET)
    @ResponseBody
    public String addCourse(@RequestParam(name = "s_id") String subjectId,
                            @RequestParam(name = "c_id") String courseId){
        Subject subject = null;
        Course course = null;
        try{
            Optional<Subject> foundSubject = subjectDao.findById(Long.parseLong(subjectId));
            if(!foundSubject.isPresent())
                return "Null subject!";
            subject = foundSubject.get();
            Optional<Course> foundCourse = courseDao.findById(Long.parseLong(courseId));
            if(!foundCourse.isPresent())
                return "Null course!";
            course = foundCourse.get();
            subject.addCourse(course);
            subjectDao.save(subject);
            return "Course added successfully!";
        }catch(Exception ex){
            return ex.getMessage();
        }
    }

    @RequestMapping(value = "/enrollStudent", method = RequestMethod.GET)
    @ResponseBody
    public String enrollStudent(@RequestParam(name = "s_id") String subjectId,
                                @RequestParam(name = "stud_id") String studentId){
        Student student = null;
        Subject subject = null;
        try{
            Optional<User> foundStudent = userDao.findById(Long.parseLong(studentId));
            if(!foundStudent.isPresent())
                return "Null student!";
            student = (Student)foundStudent.get();
            Optional<Subject> foundSubject = subjectDao.findById(Long.parseLong(subjectId));
            if(!foundSubject.isPresent())
                return "Null subject!";
            subject = foundSubject.get();
            subject.addObserver(student);
            subjectDao.save(subject);
            return "Student enrolled in this subject successfully!";
        }catch(Exception ex){
            return ex.getMessage();
        }

    }


}
