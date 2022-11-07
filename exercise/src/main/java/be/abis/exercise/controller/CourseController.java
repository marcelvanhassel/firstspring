package be.abis.exercise.controller;

import be.abis.exercise.dto.ApiError;
import be.abis.exercise.exceptions.CourseAlreadyExistsException;
import be.abis.exercise.exceptions.CourseNotFoundException;
import be.abis.exercise.model.Course;
import be.abis.exercise.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api")
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping(path = "courses")
    public List<Course> findAllCourses() {
        return courseService.findAllCourses();
    }

    @GetMapping("courses/{id}")
    //@Valid
    public ResponseEntity<? extends Object> findCourseById(@PathVariable("id") @Min(value=1000, message="Id cannot contain less than 4 digits") @Max(value=9999, message="Id cannot contain more than 4 digits") int id) throws CourseNotFoundException {
            Course course = courseService.findCourse(id);
            return new ResponseEntity<Course>(course, HttpStatus.OK);
    }

    @GetMapping("courses/title/{title}")
    public ResponseEntity<? extends Object> findCourseByTitle(@PathVariable String title) throws CourseNotFoundException {
        Course course = courseService.findCourse(title);
        return new ResponseEntity<Course>(course, HttpStatus.OK);
    }

    @PostMapping(path = "courses")
    public ResponseEntity<? extends Object> addCourse(@Valid @RequestBody Course course) throws CourseAlreadyExistsException {
        Course addedCourse = courseService.addCourse(course);
        return new ResponseEntity<Course>(addedCourse, HttpStatus.OK);
    }

}
