package com.codegym.luxstay.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private User user;
    @ManyToOne
    private Apartment apartment;

    public Like(User user, Apartment apartment) {
        this.user = user;
        this.apartment = apartment;
    }

    public Like() {
    }
}
