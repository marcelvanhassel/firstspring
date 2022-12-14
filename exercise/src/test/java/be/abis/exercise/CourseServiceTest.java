package be.abis.exercise;

import be.abis.exercise.model.Course;
import be.abis.exercise.service.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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

    @Test
    public void oneCourseShouldBeCheap() {
        // Arrange
        List<Course> myCourses = cs.findAllCoursesCheaperThan500();
        // Act
        int amount = myCourses.size();
        // Assert
        assertEquals(2, amount);
    }

}
