package com.codegym.luxstay.controller;

import com.codegym.luxstay.model.Apartment;
import com.codegym.luxstay.model.Image;
import com.codegym.luxstay.service.impl.ApartmentServiceImpl;
import com.codegym.luxstay.service.iservice.IApartment;
import com.codegym.luxstay.service.iservice.IImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.HashSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/apartments")
public class ApartmentControllerAPI {
    @Autowired
    IApartment apartmentService;

    @Autowired
    IImage imageService;

    @GetMapping("")
    public ResponseEntity<Iterable<Apartment>> getAllApartment() {
        return new ResponseEntity<>(apartmentService.findAll(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Apartment> saveApartment(@RequestBody Apartment apartment) {
        apartmentService.save(apartment);
        if (apartment.getImageList() != null) {
            for (Image image : apartment.getImageList()) {
                image.setApartmentImage(apartment);
                imageService.save(image);
            }
        }
        return new ResponseEntity<>(apartmentService.save(apartment), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Apartment>> findById(@PathVariable Long id) {
        Optional<Apartment> apartmentOptional = apartmentService.findById(id);
        if (apartmentOptional.isPresent()) {
            return new ResponseEntity<>(apartmentOptional, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Apartment> editById(@PathVariable Long id, @RequestBody Apartment apartment) {
        Optional<Apartment> apartmentOptional = apartmentService.findById(id);
        if (!apartmentOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        apartment.setUser(apartmentOptional.get().getUser());
        apartment.setId(apartmentOptional.get().getId());
        apartmentService.save(apartment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Apartment> deleteById(@PathVariable Long id) {
        Optional<Apartment> apartmentOptional = apartmentService.findById(id);
        if (!apartmentOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        apartmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/top-5")
    public ResponseEntity<Iterable<Apartment>> findTop5() {
        return new ResponseEntity<>(apartmentService.findTop5(), HttpStatus.OK);
    }

    @GetMapping("/find-not-available")
    public ResponseEntity<Iterable<Apartment>> find() {
        return new ResponseEntity<>(apartmentService.findApartmentNotAvailable(), HttpStatus.OK);
    }

    @GetMapping("/find-available")
    public ResponseEntity<Iterable<Apartment>> findApartmentAvailable() {
        return new ResponseEntity<>(apartmentService.findApartmentAvailable(), HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity<Iterable<Apartment>> findd(@RequestParam("name") String name, @RequestParam("startDate") Date startDate) {
        if (name == "") {
            return new ResponseEntity<>(apartmentService.searchManyDate(startDate), HttpStatus.OK);
        } else if (startDate == null) {
            return new ResponseEntity<>(apartmentService.searchByString(name), HttpStatus.OK);
        } else
            return new ResponseEntity<>(apartmentService.searchMany1(name, startDate), HttpStatus.OK);
    }

    @GetMapping("/findAllByUserId/{id}")
    public ResponseEntity<Iterable<Apartment>> findAllByUserId(@PathVariable long id) {
        Iterable<Apartment> iterable = apartmentService.findAllByUserId(id);
        return new ResponseEntity<>(iterable, HttpStatus.OK);
    }

    @GetMapping("/search/")
    public ResponseEntity<Iterable<Apartment>> searchByCity(@RequestParam String city) {
        return new ResponseEntity<>(apartmentService.findAllByCityContaining(city), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Apartment>> searchByAllRoundAddress(@RequestParam String city, String district, String ward) {
        return new ResponseEntity<>(apartmentService.findAllByCityContainingAndDistrictContainingAndWardContaining(city, district, ward), HttpStatus.OK);
    }

    @GetMapping("/newest-apartments")
    public ResponseEntity<Iterable<Apartment>> newestApartment() {
        return new ResponseEntity<>(apartmentService.find8Newest(), HttpStatus.OK);
    }

    @PutMapping("/set-status")
    public ResponseEntity<Optional<Apartment>> setApartmentStatus(@RequestParam long id, @RequestBody Apartment apartment) {
        Optional<Apartment> selected = apartmentService.findById(id);
        if (selected.isPresent()) {
            apartment.setId(selected.get().getId());
            apartment.setApartmentType(selected.get().getApartmentType());
            apartment.setAddress(selected.get().getAddress());
            apartment.setBathRoom(selected.get().getBathRoom());
            apartment.setBethRoom(selected.get().getBethRoom());
            apartment.setCity(selected.get().getCity());
            apartment.setDistrict(selected.get().getDistrict());
            apartment.setWard(selected.get().getWard());
            apartment.setDescription(selected.get().getDescription());
            apartment.setName(selected.get().getName());
            apartment.setUser(selected.get().getUser());
            apartment.setPrice(selected.get().getPrice());
            apartmentService.save(apartment);
            return new ResponseEntity<>(selected, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
        @GetMapping("/search-price")
        public ResponseEntity<Iterable<Apartment>> searchByPrice (@RequestParam("price1") Double
        price1, @RequestParam("price2") Double price2){
            return new ResponseEntity<>(apartmentService.findbyPrice(price1, price2), HttpStatus.OK);
        }
        @GetMapping("/search-all")
        public ResponseEntity<Iterable<Apartment>> searchByAll (@RequestParam("value") String
        value, @RequestParam("typeID") String typeID, @RequestParam("price1") String
        price1, @RequestParam("price2") String price2){
            Long typeApartment_id = 0L;
            Double price11 = 0d;
            Double price22 = 0d;
            if (!price1.isEmpty()) {
                price11 = Double.parseDouble(price1);
            }
            if (!price2.isEmpty()) {
                price22 = Double.parseDouble(price2);
            }
            if (!typeID.isEmpty()) {
                typeApartment_id = Long.parseLong(typeID);
            }

            return new ResponseEntity<>(apartmentService.findByAll(value, typeApartment_id, price11, price22), HttpStatus.OK);
        }
    }
