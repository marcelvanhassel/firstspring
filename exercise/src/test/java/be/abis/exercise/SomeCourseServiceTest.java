package be.abis.exercise;

import be.abis.exercise.model.Course;
import be.abis.exercise.service.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SomeCourseServiceTest {

    @Autowired @Qualifier("SomeCourse")
    CourseService cs;

    @Test
    public void foundCourseShouldBeTest() {
        // Arrange
        int id = 7900;
        // Act
        Course foundCourse = cs.findCourse(id);
        // Assert
        assertEquals("Test Course", foundCourse.getLongTitle());
    }
}
