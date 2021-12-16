package com.codegym.luxstay.service.impl;

import com.codegym.luxstay.model.Role;
import com.codegym.luxstay.model.RoleName;
import com.codegym.luxstay.repository.IRoleRepository;
import com.codegym.luxstay.service.iservice.IRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements IRole {
    @Autowired
    IRoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleRepository.findByName(name);
    }
}
