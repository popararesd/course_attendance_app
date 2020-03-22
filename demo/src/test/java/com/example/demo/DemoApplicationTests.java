package com.example.demo;

import com.example.demo.databaseAccess.*;
import com.example.demo.entity.*;
import com.example.demo.factory.UserFactory;
import com.example.demo.factory.UserTypes;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Rule;
import static org.junit.Assert.*;


@SpringBootTest
class DemoApplicationTests {

	private User user,user2;
	private Student student;
	private Professor prof;
	private Course course,course2;
	private Attendance attendance,attendance1;
	@Mock
	private UserDao userDao;
	private CourseDao courseDao;
	private AttendanceDao attendanceDao;
	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Before
	@Autowired
	public void init(UserDao userDao,CourseDao courseDao,AttendanceDao attendanceDao){
		this.userDao=userDao;
		this.courseDao = courseDao;
		this.attendanceDao=attendanceDao;

		user = UserFactory.buildUser(UserTypes.STUDENT);
		user.setName("Rar");
		user.setPhoneNumber("12345");
		user.setEmail("poparar");
		((Student)user).setIdentificationNumber("1234");
		((Student)user).setRegistrationNumber("blabla");
		userDao.save(user);
		student = (Student)userDao.findByEmail(user.getEmail());
		user2 = UserFactory.buildUser(UserTypes.PROFESSOR);
		user2.setName("Paul");
		user2.setPhoneNumber("7777");
		user2.setEmail("paulpaul");
		((Professor)user2).setDepartment("CTI");
		userDao.save(user2);
		prof = (Professor) userDao.findByEmail(user2.getEmail());
		course = new Course();
		course.setProfessor((Professor) prof);
		course.setCredits(5);
		course.setName("CURS_TEST");
		courseDao.save(course);
		attendance = new Attendance();
		attendance.setStudent((Student)student);
		course2 = courseDao.findByName(course.getName());
		attendance.setCourse(course2);
		attendanceDao.save(attendance);
		attendance1 = attendanceDao.findByCourse(course2);

		attendanceDao.deleteById(attendance1.getAttendanceId());
		courseDao.deleteById(course2.getId());
		userDao.deleteById(prof.getId());
		userDao.deleteById(student.getId());
	}

	@Test
	void test1() {
		User user = UserFactory.buildUser(UserTypes.STUDENT);
		user.setName("Rar");
		user.setPhoneNumber("12345");
		user.setEmail("poparar");
		((Student)user).setIdentificationNumber("1234");
		((Student)user).setRegistrationNumber("blabla");
		userDao.save(user);
		User ret = userDao.findByEmail(user.getEmail());
		userDao.deleteById(ret.getId());
		assertTrue(((Student)user).equals((Student)ret));
	}



	@Test
	void test2(){

		assertTrue(((Student)user).equals((Student)student));

	}

	@Test
	public void test3(){
		assertTrue(((Professor)user2).equals((Professor) prof));
	}

	@Test
	public void test4(){
		assertTrue(course.equals((Course)course2));
	}

	@Test
	public void test5(){
		assertTrue(attendance.equals((Attendance)attendance1));
	}

}
