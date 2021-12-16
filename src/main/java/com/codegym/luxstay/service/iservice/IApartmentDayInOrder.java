package com.codegym.luxstay.service.iservice;

import com.codegym.luxstay.model.ApartmentDayInOrder;
import com.codegym.luxstay.service.IGeneralService;

public interface IApartmentDayInOrder extends IGeneralService<ApartmentDayInOrder> {
    Iterable<ApartmentDayInOrder> findAllByApartmentId(long apartmentId);
}
