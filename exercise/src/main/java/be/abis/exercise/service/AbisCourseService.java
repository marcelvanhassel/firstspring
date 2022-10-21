package be.abis.exercise.service;

import be.abis.exercise.model.Course;
import be.abis.exercise.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
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
    public Course findCourse(int id) {
        return cr.findCourse(id);
    }

    @Override
    public Course findCourse(String shortTitle) {
        return cr.findCourse(shortTitle);
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


    @Value("#{memoryCourseRepository.courses.?[pricePerDay lt 500]}")
    public void setAllCheapCourses(List<Course> cheapCourses) {
        this.coursesCheaperThan500 = cheapCourses;
    }

    @Override
    public List<Course> findAllCoursesCheaperThan500() {
        return this.coursesCheaperThan500;
    }
}
