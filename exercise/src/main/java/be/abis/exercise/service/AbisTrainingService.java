package be.abis.exercise.service;

import be.abis.exercise.exceptions.EnrollException;
import be.abis.exercise.model.Course;
import be.abis.exercise.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class AbisTrainingService implements TrainingService {
    // Attributes
    private CourseService courseService;
    private PersonService personService;
    private String welcomeString;

    // Constructors
    public AbisTrainingService() {

    }

    @PostConstruct
    public void init() {
        System.out.println("AbisTrainingService is ready for work!");
    }

    // Getters and Setters
    public CourseService getCourseService() {
        return courseService;
    }

    public PersonService getPersonService() {
        return personService;
    }


    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public String getWelcomeString() {
        return welcomeString;
    }

    @Value("Welkom welkom!")
    public void setWelcomeString(String welcomeString) {
        this.welcomeString = welcomeString;
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
        if (date.isBefore(LocalDate.now())) {
            throw new EnrollException("Can't enroll for session in the past!");
        } else {
            System.out.println(person.getFirstName() + " is now enrolled for " + course.getShortTitle() + " on " + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }

    }
}
