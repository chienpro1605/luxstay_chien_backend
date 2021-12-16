package com.codegym.luxstay.repository;

import com.codegym.luxstay.model.ApartmentType;
import com.codegym.luxstay.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageRepository extends JpaRepository<Image, Long> {
}
