package com.codegym.luxstay.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @ManyToOne
    private User user;

    public Notification() {
    }

    public Notification(String content, User user) {
        this.content = content;
        this.user = user;
    }

    public Notification(Long id, String content, User user) {
        this.id = id;
        this.content = content;
        this.user = user;
    }
}
