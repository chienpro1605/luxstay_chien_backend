package com.codegym.luxstay.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter @Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 6, max = 24)
    @Column(unique = true)
    private String username;
    @JsonIgnore
    @NotBlank
    @Size(min = 6, max = 100)
    private String password;
    @NotBlank
    @Size(min = 10, max = 11)
    private String phone;
    @Size(min = 2, max = 24)
    private String fullName;
    @Size(max = 64)
    @Email
    private String email;
    @Lob
    private String avatar;
    private String city;
    private String address;

    @ManyToMany(fetch = FetchType.EAGER)
    Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(@NotBlank @Size(min = 3, max = 50) String username,
                @NotBlank @Size(min = 6, max = 100) String encode,
                @NotBlank @Size(max = 10,min = 10) String phone) {
        this.username = username;
        this.password = encode;
        this.phone = phone;
    }

    public User(String username, String password, String phone, String fullName, String email, String avatar, String city, String address, Set<Role> roles) {
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
}
