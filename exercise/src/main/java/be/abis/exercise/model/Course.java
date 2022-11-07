package be.abis.exercise.model;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

public class Course {

	@Size(min=4, max=4, message="A courseId should contain 4 digits")
	@Pattern(regexp="\\d{4}")
	@NotEmpty(message="Course should hav an ID")
    private String courseId;
	private String shortTitle;
	@NotEmpty(message="Title must be filled in!")
	private String longTitle;
	@Min(value = 1, message = "Every course takes at least 1 day")
	private int numberOfDays;
	private double pricePerDay;
	
	public Course(){}
	
	public Course(String courseId, String shortTitle, String longTitle, int numberOfDays, double pricePerDay) {
		super();
		this.courseId = courseId;
		this.shortTitle = shortTitle;
		this.longTitle = longTitle;
		this.numberOfDays = numberOfDays;
		this.pricePerDay = pricePerDay;
	}
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	public String getShortTitle() {
		return shortTitle;
	}
	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}
	public String getLongTitle() {
		return longTitle;
	}
	public void setLongTitle(String longTitle) {
		this.longTitle = longTitle;
		if(this.getShortTitle() == null) {
			this.shortTitle = longTitle;
		}
	}
	public int getNumberOfDays() {
		return numberOfDays;
	}
	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}
	public double getPricePerDay() {
		return pricePerDay;
	}
	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	// Equals and hashcode
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Course course = (Course) o;
		return numberOfDays == course.numberOfDays && longTitle.equals(course.longTitle);
	}

	@Override
	public int hashCode() {
		return Objects.hash(longTitle, numberOfDays);
	}
}