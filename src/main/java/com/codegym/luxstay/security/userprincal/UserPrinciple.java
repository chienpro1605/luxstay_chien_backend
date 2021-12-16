package com.codegym.luxstay.security.userprincal;

import com.codegym.luxstay.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter
public class UserPrinciple implements UserDetails {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private String phone;
    private String fullName;
    private String email;
    private String avatar;
    private String city;
    private String address;
    private Collection<? extends GrantedAuthority> roles;

    public UserPrinciple(Long id, String username, String password, String phone, String fullName, String email, String avatar, String city, String address, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.fullName = fullName;
        this.email = email;
        this.avatar = avatar;
        this.city = city;
        this.address = address;
        this.roles = roles;
    }

    public UserPrinciple() {
    }
    public static UserPrinciple build(User user){
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
        return new UserPrinciple( user.getId(), user.getUsername(),user.getPassword(),user.getPhone(),user.getFullName(),user.getEmail(),
                user.getAvatar(),user.getCity(),user.getAddress(),
                authorities
        );
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
