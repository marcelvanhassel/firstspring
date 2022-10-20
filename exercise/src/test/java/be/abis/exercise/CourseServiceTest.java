package be.abis.exercise;

import be.abis.exercise.model.Course;
import be.abis.exercise.service.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CourseServiceTest {

    @Autowired
    CourseService cs;

    @Test
    public void foundCourseShouldBeSQL() {
        // Arrange
        int id = 7900;
        // Act
        Course foundCourse = cs.findCourse(id);
        // Assert
        assertEquals("Workshop SQL", foundCourse.getLongTitle());
    }

}
