package be.abis.exercise.service;

import be.abis.exercise.exceptions.EnrollException;
import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class AbisTrainingService implements TrainingService {
    // Attributes
    @Autowired
    private CourseService courseService;
    @Autowired
    private PersonService personService;

    @Value("Welkom welkom!")
    private String welcomeString;

    // Constructors
    public AbisTrainingService() {
        System.out.println("AbisTrainingService is ready for work!");
    }
    // Getters and Setters
    public CourseService getCourseService() {
        return courseService;
    }

    public PersonService getPersonService() {
        return personService;
    }

    // Implement other methods
    @Override
    public String getWelcomeMessage() {
        return this.welcomeString;
    }

    @Override
    public List<Course> showFollowedCourses(Person person) {
        return null;
    }

    @Override
    public void enrollForSession(Person person, Course course, LocalDate date) throws EnrollException {

    }
}
