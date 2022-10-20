package be.abis.exercise;

import be.abis.exercise.service.AbisTrainingService;
import be.abis.exercise.service.CourseService;
import be.abis.exercise.service.PersonService;
import be.abis.exercise.service.TrainingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ExerciseApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExerciseApplication.class, args);
    }

    @Bean
    public TrainingService trainingService(PersonService ps, CourseService cs) {
        TrainingService ts = new AbisTrainingService();
        ts.setCourseService(cs);
        ts.setPersonService(ps);
        return ts;
    }

}
