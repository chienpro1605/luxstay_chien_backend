package com.codegym.luxstay.service.iservice;

import com.codegym.luxstay.model.Role;
import com.codegym.luxstay.model.RoleName;

import java.util.Optional;

public interface IRole {
    Optional<Role> findByName(RoleName name);
}
