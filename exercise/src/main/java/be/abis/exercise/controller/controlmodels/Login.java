package be.abis.exercise.controller.controlmodels;

public class Login {
    // Attributes
    private String email;
    private String password;
    // Constructor
    public Login() {
    }

    public Login(String username, String password) {
        this.email = username;
        this.password = password;
    }

    // Getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
