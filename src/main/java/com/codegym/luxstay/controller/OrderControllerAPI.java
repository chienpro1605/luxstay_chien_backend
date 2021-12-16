package com.codegym.luxstay.controller;

import com.codegym.luxstay.model.ApartmentDayInOrder;
import com.codegym.luxstay.model.Order;
import com.codegym.luxstay.service.impl.ApartmentDayInOrderServiceImpl;
import com.codegym.luxstay.service.iservice.IApartmentDayInOrder;
import com.codegym.luxstay.service.iservice.IOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;


@RestController
@RequestMapping("api/orders")
@CrossOrigin("*")

public class OrderControllerAPI {

    @Autowired
    private IOrder orderService;

    @Autowired
    private IApartmentDayInOrder apartmentDayInOrder;

    private final long oneDay = 86400000;
    @GetMapping("")
    public ResponseEntity<Iterable<Order>> findAll(){
        return new ResponseEntity<>(orderService.findAll(),HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Order> create (@RequestBody Order order){
        order.setCheckIn(false);
        order.setOrderStatus("Pending");
        long startDate = order.getStartDate().getTime();
        long endDate = order.getEndDate().getTime();
        for(long i = startDate; i < endDate; i+=oneDay) {
            Date newDay = new Date(i);
            ApartmentDayInOrder dayInOrder = new ApartmentDayInOrder();
            dayInOrder.setDayInOrder(newDay);
            dayInOrder.setDayInOrderApartment(order.getApartment());
            apartmentDayInOrder.save(dayInOrder);
        }
        orderService.save(order);
        return new ResponseEntity<>(orderService.save(order),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> update(@PathVariable Long id,@RequestBody Order order) {
        Optional<Order> optionalOrder = orderService.findById(id);
        if(!optionalOrder.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        order.setId(optionalOrder.get().getId());
        orderService.save(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Order> delete(@PathVariable Long id){
        Optional<Order> optionalOrder = orderService.findById(id);
        if(!optionalOrder.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @GetMapping("/findByApartment/{apartmentId}")
    public ResponseEntity<Iterable<Order>> findAllByApartmentId(@PathVariable long apartmentId) {
        return new ResponseEntity<>(orderService.findAllByApartmentId(apartmentId),HttpStatus.OK);
    }
    @GetMapping("/findByUser/{userId}")
    public ResponseEntity<Iterable<Order>> findAllByUserId(@PathVariable long userId) {
        return new ResponseEntity<>(orderService.findAllByUserId(userId),HttpStatus.OK);
    }
    @GetMapping("/findOrderPending/{apartmentId}")
    public ResponseEntity<Iterable<Order>> findAllByStatusAndApartment(@PathVariable long apartmentId) {
        return new ResponseEntity<>(orderService.findAllOrderOfApartmentWithPending(apartmentId), HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Iterable<Order>> findByUserId(@PathVariable Long id){
        return new ResponseEntity<>(orderService.findAllByUserId(id), HttpStatus.OK);
    }

    @GetMapping("/get-apartment/{apartmentID}")
    public ResponseEntity<Iterable<Order>> findByApartmentID(@PathVariable Long apartmentID){
        return new ResponseEntity<>(orderService.findAllByApartment(apartmentID), HttpStatus.OK);    
   }

}
