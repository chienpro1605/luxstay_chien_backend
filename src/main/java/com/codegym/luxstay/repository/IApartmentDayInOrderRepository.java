package com.codegym.luxstay.repository;

import com.codegym.luxstay.model.ApartmentDayInOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IApartmentDayInOrderRepository extends JpaRepository<ApartmentDayInOrder, Long> {
    @Query(value = "select * from apartment_day_in_order where apartment_id =?1 ",nativeQuery = true)
    Iterable<ApartmentDayInOrder> findAllByApartmentId(long apartmentId);
}
