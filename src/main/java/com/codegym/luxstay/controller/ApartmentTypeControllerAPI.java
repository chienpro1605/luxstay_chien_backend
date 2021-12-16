package com.codegym.luxstay.controller;

import com.codegym.luxstay.model.Apartment;
import com.codegym.luxstay.model.ApartmentType;
import com.codegym.luxstay.model.User;
import com.codegym.luxstay.service.impl.ApartmentTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("api/apartment-types")
public class ApartmentTypeControllerAPI {
    @Autowired
    ApartmentTypeServiceImpl apartmentTypeService;

    @GetMapping("")
    public ResponseEntity<Iterable<ApartmentType>> getAll() {
        return new ResponseEntity<>(apartmentTypeService.findAll(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ApartmentType> save(@RequestBody ApartmentType apartmentType) {
        return new ResponseEntity<>(apartmentTypeService.save(apartmentType), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApartmentType> findById(@PathVariable Long id) {
        Optional<ApartmentType> apartmentTypeOptional = apartmentTypeService.findById(id);
        if(!apartmentTypeOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(apartmentTypeOptional.get(),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateApartmentType (@PathVariable Long id, @RequestBody ApartmentType apartmentType){
        Optional<ApartmentType> selected = apartmentTypeService.findById(id);
        if(!selected.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        apartmentType.setId(selected.get().getId());
        apartmentTypeService.save(apartmentType);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApartmentType> deleteById(@PathVariable Long id) {
        Optional<ApartmentType> apartmentOptional = apartmentTypeService.findById(id);
        if (!apartmentOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        apartmentTypeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
