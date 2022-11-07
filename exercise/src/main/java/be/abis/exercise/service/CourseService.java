package be.abis.exercise.service;

import be.abis.exercise.exceptions.CourseAlreadyExistsException;
import be.abis.exercise.exceptions.CourseNotFoundException;
import be.abis.exercise.model.Course;

import java.util.List;

public interface CourseService {
    public List<Course> findAllCourses();
    public Course findCourse(int id) throws CourseNotFoundException;
    public Course findCourse(String shortTitle) throws CourseNotFoundException;
    public Course addCourse(Course c) throws CourseAlreadyExistsException;
    public Course updateCourse(Course c);
    public Boolean deleteCourse(Course c);

    public List<Course> findAllCoursesCheaperThan500();
}
