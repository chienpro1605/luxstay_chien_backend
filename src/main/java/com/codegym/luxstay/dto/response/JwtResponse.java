package com.codegym.luxstay.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter @Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String name;
    private long id;
    private String avatar;
    private Collection<?extends GrantedAuthority> roles;


    public JwtResponse() {
    }
    public JwtResponse(String token, String name, Collection<? extends GrantedAuthority> roles) {
        this.token = token;
        this.name = name;
        this.roles = roles;
    }

    public JwtResponse(String token,  String name, long id, String avatar, Collection<? extends GrantedAuthority> roles) {
        this.token = token;
        this.name = name;
        this.roles = roles;
        this.id = id;
        this.avatar = avatar;
    }
}
