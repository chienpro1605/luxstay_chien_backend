package com.codegym.luxstay.repository;

import com.codegym.luxstay.model.ApartmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IApartmentTypeRepository extends JpaRepository<ApartmentType, Long> {
}
