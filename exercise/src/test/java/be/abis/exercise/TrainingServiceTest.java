package be.abis.exercise;

import be.abis.exercise.model.Course;
import be.abis.exercise.service.TrainingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
