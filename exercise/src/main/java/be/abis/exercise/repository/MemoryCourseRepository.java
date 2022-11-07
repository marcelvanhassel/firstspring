package be.abis.exercise.repository;

import be.abis.exercise.exceptions.CourseAlreadyExistsException;
import be.abis.exercise.exceptions.CourseNotFoundException;
import be.abis.exercise.model.Course;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class MemoryCourseRepository implements CourseRepository {
	
	private ArrayList<Course> courses = new ArrayList<Course>();
	
	
	public MemoryCourseRepository(){
		courses.add(new Course("7850","DB2, an overview","DB2, an overview",5,550.0));
		courses.add(new Course("7900","Workshop SQL","Workshop SQL",3,475.0));
		courses.add(new Course("8000","Java Prog","Java Programming",5,500.0));
		courses.add(new Course("8050","Maven","Maven",1,450.0));
		courses.add(new Course("8100","Spring","Programming with Spring",3,525.0));
	}

	public ArrayList<Course> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}

	
	@Override
	public List<Course> findAllCourses() {
		return courses;
	}

	@Override
	public Course findCourse(int id) throws CourseNotFoundException {
		return courses.stream().filter(c->c.getCourseId().equals(id+"")).findFirst().orElseThrow(() -> new CourseNotFoundException("No course with id: " + id + " found!"));
	}

	@Override
	public Course findCourse(String shortTitle) throws CourseNotFoundException {
		return courses.stream().filter(c->c.getShortTitle().equalsIgnoreCase(shortTitle)).findFirst().orElseThrow(() -> new CourseNotFoundException("No course with " + shortTitle + " found!"));
	}

	// Extra implementations
	@Override
	public Course addCourse(Course c) throws CourseAlreadyExistsException {
		if(!courses.contains(c)) {
			courses.add(c);
			return c;
		} else {
			throw new CourseAlreadyExistsException("Course " + c.getLongTitle() + " allready exists!");
		}
	}

	@Override
	public Course updateCourse(Course c) {
		if (courses.contains(c)) {
			courses.set(courses.indexOf(c), c);
			return c;
		}
		return null;
	}

	@Override
	public boolean deleteCourse(Course c) {
		if(courses.contains(c)) {
			courses.remove(c);
					return true;
		}
		return false;
	}
}
