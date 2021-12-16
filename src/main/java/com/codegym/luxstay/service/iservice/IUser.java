package com.codegym.luxstay.service.iservice;

import com.codegym.luxstay.model.User;
import com.codegym.luxstay.service.IGeneralService;

import java.util.Optional;

public interface IUser extends IGeneralService<User> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
//    Boolean existsByPhone(String phone);
}
