package com.codegym.luxstay.service.iservice;

import com.codegym.luxstay.model.Order;
import com.codegym.luxstay.service.IGeneralService;

public interface IOrder extends IGeneralService<Order> {

    Iterable<Order> findAllOrderOfUserHasApartment(long apartmentUserId);
    Iterable<Order> findAllByUserId(Long user_id);
    Iterable<Order> findAllByApartmentId(Long apartment_id);
    Iterable<Order> findAllOrderOfApartmentWithPending(long apartmentId);

    Iterable<Order>findAllByUser(Long id);
    Iterable<Order> findAllByApartment(Long apartmentID);

}
