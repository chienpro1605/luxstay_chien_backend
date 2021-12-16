package com.codegym.luxstay.service.impl;

import com.codegym.luxstay.model.ApartmentType;
import com.codegym.luxstay.repository.IApartmentRepository;
import com.codegym.luxstay.repository.IApartmentTypeRepository;
import com.codegym.luxstay.service.iservice.IApartmentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;
@Service

public class ApartmentTypeServiceImpl implements IApartmentType {
    @Autowired
    IApartmentTypeRepository apartmentTypeRepository;
    @Override
    public Iterable<ApartmentType> findAll() {
        return apartmentTypeRepository.findAll();
    }

    @Override
    public Optional<ApartmentType> findById(long id) {
        return apartmentTypeRepository.findById(id);
    }

    @Override
    public ApartmentType save(ApartmentType apartmentType) {
        return apartmentTypeRepository.save(apartmentType);
    }

    @Override
    public void delete(long id) {
        apartmentTypeRepository.deleteById(id);
    }
}
