package com.example.demo.controllers;

import com.example.demo.databaseAccess.AttendanceDao;
import com.example.demo.databaseAccess.CourseDao;
import com.example.demo.databaseAccess.UserDao;
import com.example.demo.entity.Attendance;
import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/attendace")
public class AttendaceController {

    private AttendanceDao attendanceDao;
    private CourseDao courseDao;
    private UserDao userDao;

    @Autowired
    public AttendaceController(CourseDao courseDao,UserDao userDao,AttendanceDao attendanceDao){
        this.courseDao=courseDao;
        this.userDao=userDao;
        this.attendanceDao=attendanceDao;
    }

    /**
     * Inserts a new Attendence into the database.
     * @param  courseId The id of the course to be attended
     * @param  studentId The id of the student atendee
     * @return      a String for success of failure
     * @see         Attendance
     */
    @RequestMapping(value = "/markAttendace", method = RequestMethod.GET)
    @ResponseBody
    public String markAttendace(@RequestParam(name = "course_id") String courseId,
                                @RequestParam(name = "stud_id") String studentId){
        try {
            Optional<Course> c = courseDao.findById(Long.parseLong(courseId));
            if (!c.isPresent())
                return "Null course";
            Optional<User> s = userDao.findById(Long.parseLong(studentId));
            if (!s.isPresent() || !(s.get() instanceof Student))
                return "Null student";
            Course course = c.get();
            Student student = (Student) s.get();
            Attendance attendance = new Attendance();
            attendance.setCourse(course);
            attendance.setStudent(student);
            attendanceDao.save(attendance);
            return "Student has been marked!";
        }
        catch(Exception ex){
            return ex.getMessage();
        }
    }
    /**
     * Returns a List of the Students who attended the lecture
     * @param  courseId The id of the course
     * @return      a list of Students
     * @see         Attendance,Student
     */
    @RequestMapping(value = "/getAttendees", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Student> getAtendees(@RequestParam(name = "course_id") String courseId){
        Attendance attendance = null;
        List<Attendance> list = null;
        try{
            Optional<Course> c = courseDao.findById(Long.parseLong(courseId));
            if (!c.isPresent())
                return null;
            list = attendanceDao.findAll();
            List<Student> new_list = new ArrayList<>();
            for(Attendance a : list) {
                if (a.getCourse().getId() == Long.parseLong(courseId))
                    new_list.add(a.getStudent());
            }
            return new_list;
        }catch(Exception ex){
            return null;
        }
    }
}
