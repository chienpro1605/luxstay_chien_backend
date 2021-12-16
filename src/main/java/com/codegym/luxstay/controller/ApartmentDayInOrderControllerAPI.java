package com.codegym.luxstay.controller;

import com.codegym.luxstay.model.ApartmentDayInOrder;
import com.codegym.luxstay.service.impl.ApartmentDayInOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("api/apartment-day-in-orders")
public class ApartmentDayInOrderControllerAPI {
    @Autowired
    ApartmentDayInOrderServiceImpl apartmentDayInOrderService;

    @GetMapping("")
    public ResponseEntity<Iterable<ApartmentDayInOrder>> getAllApartment() {
        return new ResponseEntity<>(apartmentDayInOrderService.findAll(), HttpStatus.OK);

    }

    @PostMapping("")
    public ResponseEntity<ApartmentDayInOrder> saveApartment(@RequestBody ApartmentDayInOrder apartmentDayInOrder) {
        return new ResponseEntity<>(apartmentDayInOrderService.save(apartmentDayInOrder), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApartmentDayInOrder> findById(@PathVariable Long id) {
        Optional<ApartmentDayInOrder> apartmentOptional = apartmentDayInOrderService.findById(id);
        if(!apartmentOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(apartmentOptional.get(),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApartmentDayInOrder> editById(@PathVariable Long id,@RequestBody ApartmentDayInOrder apartmentDayInOrder) {
        Optional<ApartmentDayInOrder> apartmentOptional = apartmentDayInOrderService.findById(id);
        if(!apartmentOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        apartmentDayInOrder.setId(apartmentOptional.get().getId());
        apartmentDayInOrderService.save(apartmentDayInOrder);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApartmentDayInOrder> deleteById(@PathVariable Long id) {
        Optional<ApartmentDayInOrder>  apartmentDayInOrder = apartmentDayInOrderService.findById(id);
        if(!apartmentDayInOrder.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        apartmentDayInOrderService.delete(id);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/findAllByApartment/{apartmentId}")
    public ResponseEntity<Iterable<ApartmentDayInOrder>> findAllByApartmentId(@PathVariable long apartmentId) {
        return new ResponseEntity<>(apartmentDayInOrderService.findAllByApartmentId(apartmentId),HttpStatus.OK);
    }
}
