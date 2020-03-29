package com.example.demo;

import com.example.demo.databaseAccess.*;
import com.example.demo.entity.*;
import com.example.demo.factory.UserFactory;
import com.example.demo.factory.UserTypes;
import com.example.demo.utility.PasswordAuthentication;
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
    @Mock
    private StudentObserver student;
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    private Subject subject;

    @Test
    public void observerTest() {
        student = new Student();
        subject = new Subject();
        subject.addObserver(student);
        subject.addNewCourse(new Course(),"test");
        assertEquals(((User)student).getNewCourse(),"test");
    }

    @Test
    public void factoryTest1(){
        assertTrue(UserFactory.buildUser(UserTypes.STUDENT) instanceof Student);
    }
    @Test
    public void factoryTest2(){
        assertTrue(UserFactory.buildUser(UserTypes.PROFESSOR) instanceof Professor);
    }
    @Test
    public void factoryTest3(){
        assertTrue(UserFactory.buildUser(UserTypes.ADMIN) instanceof Admin);
    }
    @Test
    public void passwordHash(){assertTrue((new PasswordAuthentication()).authenticate("test",(new PasswordAuthentication()).hash("test")));}

}
