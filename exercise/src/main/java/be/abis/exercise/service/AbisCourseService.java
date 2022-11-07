package be.abis.exercise.service;

import be.abis.exercise.exceptions.CourseAlreadyExistsException;
import be.abis.exercise.exceptions.CourseNotFoundException;
import be.abis.exercise.model.Course;
import be.abis.exercise.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class AbisCourseService implements CourseService {
    // Attributes
    @Autowired
    CourseRepository cr;

    List<Course> coursesCheaperThan500 = new ArrayList<>();

    // Constructors
    public AbisCourseService() {
    }
    // Getters and Setters

    // Method implementations
    @Override
    public List<Course> findAllCourses() {
        return cr.findAllCourses();
    }

    @Override
    public Course findCourse(int id) throws CourseNotFoundException {
        return cr.findCourse(id);
    }

    @Override
    public Course findCourse(String shortTitle) throws CourseNotFoundException {
        return cr.findCourse(shortTitle);
    }

    @Override
    public Course addCourse(Course c) throws CourseAlreadyExistsException {
        return cr.addCourse(c);
    }

    @Override
    public Course updateCourse(Course c) {
        return cr.updateCourse(c);
    }

    @Override
    public Boolean deleteCourse(Course c) {
        return cr.deleteCourse(c);
    }


    @Value("#{memoryCourseRepository.courses.?[pricePerDay lt 500]}")
    public void setAllCheapCourses(List<Course> cheapCourses) {
        this.coursesCheaperThan500 = cheapCourses;
    }

    @Override
    public List<Course> findAllCoursesCheaperThan500() {
        return this.coursesCheaperThan500;
    }
}
