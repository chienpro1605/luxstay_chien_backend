package com.codegym.luxstay.service.impl;

import com.codegym.luxstay.model.ApartmentDayInOrder;
import com.codegym.luxstay.repository.IApartmentDayInOrderRepository;
import com.codegym.luxstay.service.iservice.IApartmentDayInOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApartmentDayInOrderServiceImpl implements IApartmentDayInOrder {
    @Autowired
    IApartmentDayInOrderRepository apartmentDayInOrderRepository;
    @Override
    public Iterable<ApartmentDayInOrder> findAll() {
        return apartmentDayInOrderRepository.findAll();
    }

    @Override
    public Optional<ApartmentDayInOrder> findById(long id) {
        return apartmentDayInOrderRepository.findById(id);
    }

    @Override
    public ApartmentDayInOrder save(ApartmentDayInOrder apartmentDayInOrder) {
        return apartmentDayInOrderRepository.save(apartmentDayInOrder);
    }

    @Override
    public void delete(long id) {
        apartmentDayInOrderRepository.deleteById(id);
    }

    @Override
    public Iterable<ApartmentDayInOrder> findAllByApartmentId(long apartmentId) {
        return apartmentDayInOrderRepository.findAllByApartmentId(apartmentId);
    }
}
