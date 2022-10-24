package be.abis.exercise;

import be.abis.exercise.exceptions.EnrollException;
import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;
import be.abis.exercise.service.TrainingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TrainingServiceTest {
    @Autowired
    TrainingService trainingService;


    @Test
    public void welcomeMessageWorks() {
        assertEquals("Welkom welkom!", trainingService.getWelcomeMessage());
    }

    @Test
    public void courseServiceIsReachable() {
        // Arrange
        int id = 7900;
        // Act
        Course foundCourse = trainingService.getCourseService().findCourse(id);
        // Assert
        assertEquals("Workshop SQL", foundCourse.getLongTitle());
    }

    @Test
    public void exceptionShouldBeThrownIfDateHasPassed() {
        Person p1 = new Person();
        p1.setFirstName("Henk");
        assertThrows(EnrollException.class, () -> trainingService.enrollForSession(p1, trainingService.getCourseService().findAllCourses().get(1), LocalDate.of(2022, 10, 23)));
    }

    @Test
    public void enrollForSessionDoesNotThrowException() {
        Person p1 = new Person();
        p1.setFirstName("Jaap");
        assertDoesNotThrow(() -> trainingService.enrollForSession(p1, trainingService.getCourseService().findAllCourses().get(1), LocalDate.now().plusDays(10)));
    }
}
