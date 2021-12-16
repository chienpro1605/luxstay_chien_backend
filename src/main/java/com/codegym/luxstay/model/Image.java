package com.codegym.luxstay.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter @Getter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String imageUrl;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "apartment_id")
    private Apartment apartmentImage;



    public Image(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Image() {
    }
}
