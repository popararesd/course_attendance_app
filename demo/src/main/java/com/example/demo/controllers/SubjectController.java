package com.example.demo.controllers;

import com.example.demo.databaseAccess.CourseDao;
import com.example.demo.databaseAccess.SubjectDao;
import com.example.demo.databaseAccess.UserDao;
import com.example.demo.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

/**
 * This class controls the flow of information for the subjects.
 */
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

    /**
     *
     * @param name The name of the subject.
     * @param credits The number of credits.
     * @param professorId The id for the professor.
     * @return The subject with the given parameters.
     */
    @RequestMapping(value = "/addSubject", method = RequestMethod.POST)
    @ResponseBody
    public Subject addSubject(@RequestParam(name = "name") String name,
                            @RequestParam(name = "cr") String credits,
                            @RequestParam(name = "prof_id") String professorId){
        Subject subject = null;
        Professor professor = null;
        try{
            subject = new Subject();
            Optional<User> foundUser = userDao.findById(Long.parseLong(professorId));
            if(!foundUser.isPresent()){
                return null;
            }
            professor = (Professor)foundUser.get();
            subject.setProfessor(professor);
            subject.setCredits(Integer.parseInt(credits));
            subject.setName(name);
            subjectDao.save(subject);
            return subject;
        }catch(Exception ex){
            return null;
        }
    }

    /**
     * This function add a course for the subject.
     * @param subjectId The id of the subject.
     * @param courseId The id of the course.
     * @return The updated subject.
     */
    @RequestMapping(value = "/addCourse", method = RequestMethod.PUT)
    @ResponseBody
    public Subject addCourse(@RequestParam(name = "s_id") String subjectId,
                            @RequestParam(name = "c_id") String courseId){
        Subject subject = null;
        Course course = null;
        try{
            Optional<Subject> foundSubject = subjectDao.findById(Long.parseLong(subjectId));
            if(!foundSubject.isPresent())
                return null;
            subject = foundSubject.get();
            Optional<Course> foundCourse = courseDao.findById(Long.parseLong(courseId));
            if(!foundCourse.isPresent())
                return null;
            course = foundCourse.get();
            subject.addCourse(course);
            subjectDao.save(subject);
            return subject;
        }catch(Exception ex){
            return null;
        }
    }

    /**
     * This function enrolls a student to a subject.
     * @param subjectId The id of the subject.
     * @param studentId The id of the student.
     * @return The updated subject.
     */
    @RequestMapping(value = "/enrollStudent", method = RequestMethod.PUT)
    @ResponseBody
    public Subject enrollStudent(@RequestParam(name = "s_id") String subjectId,
                                @RequestParam(name = "stud_id") String studentId){
        Student student = null;
        Subject subject = null;
        try{
            Optional<User> foundStudent = userDao.findById(Long.parseLong(studentId));
            if(!foundStudent.isPresent())
                return null;
            student = (Student)foundStudent.get();
            Optional<Subject> foundSubject = subjectDao.findById(Long.parseLong(subjectId));
            if(!foundSubject.isPresent())
                return null;
            subject = foundSubject.get();
            subject.addStudent(student);
            subjectDao.save(subject);
            return subject;
        }catch(Exception ex){
            return null;
        }

    }

    @RequestMapping(value = "/getSubject", method = RequestMethod.GET)
    @ResponseBody
    public Subject getSubject(@RequestParam(name = "s_id") String subjectId){
        Subject subject = null;
        try {
            Long sId = Long.parseLong(subjectId);
            subject = subjectDao.findById(sId).get();
            return subject;
        } catch (Exception ex) {
            return null;
        }
    }


}
