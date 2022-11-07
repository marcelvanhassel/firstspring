package be.abis.exercise.exceptions;

import be.abis.exercise.dto.ApiError;
import be.abis.exercise.dto.ValidationError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;

//@ControllerAdvice
@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    // Exception handling for planned exceptions
    @ExceptionHandler
    protected ResponseEntity<? extends Object> handleCourseNotFound(CourseNotFoundException cnfe, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiError ae = new ApiError("Course not found", status.value(), cnfe.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type", MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(ae, responseHeaders, status);
    }

    @ExceptionHandler
    protected ResponseEntity<? extends Object> handleCourseAlreadyExists(CourseAlreadyExistsException caee, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiError ae = new ApiError("Course not found", status.value(), caee.getMessage());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type", MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(ae, responseHeaders, status);
    }


    // ValidationErrors
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        System.out.println("Validation exception called");
        ApiError ae = new ApiError("Invalid Arguments", status.value(), ex.getMessage());
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        List<ValidationError> validationErrorList = ae.getInvalidParams();

        for (FieldError fe : fieldErrors) {
            ValidationError validationError = new ValidationError(fe.getField(), fe.getDefaultMessage());
            validationErrorList.add(validationError);
        }

        headers.add("content-type", MediaType.APPLICATION_PROBLEM_JSON_VALUE);

        return new ResponseEntity<Object>(ae, headers, status);
    }

    //@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<? extends Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
        //System.out.println("constraint violation exception invoked");
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ApiError ae = new ApiError("Arguments not in allowed range!", status.value(), ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", MediaType.APPLICATION_PROBLEM_JSON_VALUE);
        return new ResponseEntity<ApiError>(ae, headers, status);
    }

    // Type mismatch
    @ExceptionHandler
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();

        ApiError ae = new ApiError("Wrong type entered", HttpStatus.BAD_REQUEST.value(), error);
        return new ResponseEntity<Object>(ae, new HttpHeaders(), ae.getStatus());
    }

}
