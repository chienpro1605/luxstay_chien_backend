package com.codegym.luxstay.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter @Setter
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min = 6,max = 48)
    private String name;
    @ManyToOne
    private ApartmentType apartmentType;
    @NotBlank
    @Min(value = 1)
    @Max(value = 10)
    private String bethRoom;
    @NotBlank
    @Min(value = 1) @Max(value = 3)
    private String bathRoom;
    @NotBlank
    @Size(min = 1)
    private String description;
    private Double price;
    @NotBlank
    private String status;
    @OneToMany(mappedBy = "apartmentImage", cascade = CascadeType.REMOVE)
    private List<Image> imageList;
    @NotBlank
    private String city;
    @NotBlank
    private String district;
    @NotBlank
    private String ward;
    @NotBlank
    @Size(min = 3)
    private String address;
    @ManyToOne
    private User user;

    public Apartment() {
    }


    public Apartment(String name, ApartmentType apartmentType, String bethRoom, String bathRoom, String description, Double price, String status, List<Image> imageList, String city, String district, String ward, String address) {
        this.name = name;
        this.apartmentType = apartmentType;
        this.bethRoom = bethRoom;
        this.bathRoom = bathRoom;
        this.description = description;
        this.price = price;
        this.status = status;
        this.imageList = imageList;
        this.city = city;
        this.district = district;
        this.ward = ward;
        this.address = address;
    }

    public Apartment(String name, ApartmentType apartmentType, String bethRoom, String bathRoom, String description, Double price, String status, String city, String district, String ward, String address) {
        this.name = name;
        this.apartmentType = apartmentType;
        this.bethRoom = bethRoom;
        this.bathRoom = bathRoom;
        this.description = description;
        this.price = price;
        this.status = status;
        this.city = city;
        this.district = district;
        this.ward = ward;
        this.address = address;
    }
}
