package org.web2.addisyardsale.dto;

public class LoginRequest {
    private String email;
    private String password;

    // Constructors
    public LoginRequest() {}
    public LoginRequest(String Email, String Password) {
        this.email = Email;
        this.password = Password;
    }

    // Getters and setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
