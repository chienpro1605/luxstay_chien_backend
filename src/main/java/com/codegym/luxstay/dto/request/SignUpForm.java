package com.codegym.luxstay.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
public class SignUpForm {
    private String username;
    private String password;
    private String phone;
    private String fullName;
    private String email;
    private String avatar;
    private String city;
    private String address;
    private Set<String> roles;

    public SignUpForm() {
    }

    public SignUpForm(String username, String password, String phone, Set<String> roles) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.roles = roles;
    }
}
