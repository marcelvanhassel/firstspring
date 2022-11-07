package be.abis.exercise.dto;

public class ValidationError {

    // Attributes
    private String name;
    private String reason;

    // Constructor
    public ValidationError() {
    }

    public ValidationError(String name, String reason) {
        this.name = name;
        this.reason = reason;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
