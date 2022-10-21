package be.abis.exercise.service;

import be.abis.exercise.model.Course;
import be.abis.exercise.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("SomeCourse")
public class SomeCourseService implements CourseService {

    // Attributes
    CourseRepository courseRepository;

    // Constructor
    public SomeCourseService() {
    }

    // Getters and setters
    public CourseRepository getCourseRepository() {
        return courseRepository;
    }

    @Autowired
    public void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // Method implementations
    @Override
    public List<Course> findAllCourses() {
        return courseRepository.findAllCourses();
    }

    @Override
    public Course findCourse(int id) {
        Course tc;
        if ("7900".equals(""+id)) {
            tc = new Course("7900", "Test Course", "Test Course", 10, 100);
        } else {
            tc = courseRepository.findCourse(id);
        }
        return tc;
    }

    @Override
    public Course findCourse(String shortTitle) {
        return courseRepository.findCourse(shortTitle);
    }

    @Override
    public void addCourse(Course c) {
    }

    @Override
    public void updateCourse(Course c) {

    }

    @Override
    public void deleteCourse(Course c) {
    }

    @Override
    public List<Course> findAllCoursesCheaperThan500() {
        return null;
    }
}
