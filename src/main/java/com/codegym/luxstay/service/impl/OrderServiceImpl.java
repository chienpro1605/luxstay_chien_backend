package com.codegym.luxstay.service.impl;

import com.codegym.luxstay.model.Order;
import com.codegym.luxstay.repository.IOderRepository;
import com.codegym.luxstay.service.iservice.IOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements IOrder {
    @Autowired
    private IOderRepository orderRepository;

    @Override
    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void delete(long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Iterable<Order> findAllOrderOfUserHasApartment(long apartmentUserId) {
        return orderRepository.findAllOrderOfUserHasApartment(apartmentUserId);
    }

    @Override
    public Iterable<Order> findAllByUserId(Long user_id) {
        return orderRepository.findAllByUserId(user_id);
    }

    @Override
    public Iterable<Order> findAllByApartmentId(Long apartment_id) {
        return orderRepository.findAllByApartmentId(apartment_id);
    }

    @Override
    public Iterable<Order> findAllOrderOfApartmentWithPending(long apartmentId) {
        return orderRepository.findAllOrderOfApartmentWithPending(apartmentId);
    }

    @Override
    public Iterable<Order> findAllByUser(Long id) {
        return orderRepository.findAllByUser(id);
    }

    @Override
    public Iterable<Order> findAllByApartment(Long apartmentID) {
        return orderRepository.findAllByApartment(apartmentID);
    }
}
