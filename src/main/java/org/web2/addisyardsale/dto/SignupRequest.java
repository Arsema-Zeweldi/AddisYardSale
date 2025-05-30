package org.web2.addisyardsale.dto;

import lombok.Getter;
import lombok.Setter;

public class SignupRequest {

    private String fullName;
    @Setter
    @Getter
    private String email;
    @Setter
    @Getter
    private String password;

    // Constructors
    public SignupRequest() {}

    public SignupRequest(String fullName, String email, String password) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    // Getters and setters
    public String getName() {
        return fullName;
    }

    public void setName(String fullName) {
        this.fullName = fullName;
    }

}

