package be.abis.exercise.controller;

import be.abis.exercise.controller.controlmodels.Login;
import be.abis.exercise.exceptions.CourseNotFoundException;
import be.abis.exercise.model.Person;
import be.abis.exercise.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class AppController {
    @Autowired
    private TrainingService trainingService;

    @GetMapping("/")
    public String course(Model model) throws CourseNotFoundException {
        model.addAttribute("courseTitle", trainingService.getCourseService().findCourse(7900).getShortTitle());
        model.addAttribute("courseId", "7900");
        StringBuilder bothNames = new StringBuilder();
        bothNames.append(trainingService.getPersonService().findPerson(3).getFirstName()).append(" ");
        bothNames.append(trainingService.getPersonService().findPerson(3).getLastName());
        model.addAttribute("firstAndLastName", bothNames.toString());
        return "course";
    }

    @RequestMapping(value="/{myparam}")
    public String differentCourse(@PathVariable("myparam") String courseId, Model model) throws CourseNotFoundException {
        String courseIdPattern = "\\d{4}";
        Pattern ciPatterned = Pattern.compile(courseIdPattern, Pattern.CASE_INSENSITIVE);
        Matcher check = ciPatterned.matcher(courseId);

        model.addAttribute("courseId", courseId);

        String courseTitle = "Course not found";
        if (check.matches()) {
            int id = Integer.parseInt(courseId);
            courseTitle = trainingService.getCourseService().findCourse(id).getLongTitle();
        }

        model.addAttribute("courseTitle", courseTitle);

        StringBuilder bothNames = new StringBuilder();
        bothNames.append(trainingService.getPersonService().findPerson(3).getFirstName()).append(" ");
        bothNames.append(trainingService.getPersonService().findPerson(3).getLastName());
        model.addAttribute("firstAndLastName", bothNames.toString());

        return "course";

    }

    @GetMapping("/login")
    public String showForm(Model model) {
        Login login = new Login();
        model.addAttribute("login", login);
        return "login";
    }

    @PostMapping("/login")
    public String showUser(Model model, Login login) {
        StringBuilder fullName = new StringBuilder();
        Person myPerson = trainingService.getPersonService().findPerson(login.getEmail(), login.getPassword());
        fullName.append(myPerson.getFirstName()).append(" ").append(myPerson.getLastName());
        model.addAttribute("fullName", fullName);
        return "showUser";
    }

}
