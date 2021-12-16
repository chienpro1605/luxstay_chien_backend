package com.codegym.luxstay.controller;

import com.codegym.luxstay.dto.request.SignInForm;
import com.codegym.luxstay.dto.request.SignUpForm;
import com.codegym.luxstay.dto.response.JwtResponse;
import com.codegym.luxstay.dto.response.ResponseMessage;
import com.codegym.luxstay.model.Role;
import com.codegym.luxstay.model.RoleName;
import com.codegym.luxstay.model.User;
import com.codegym.luxstay.security.jwt.JwtProvider;
import com.codegym.luxstay.security.userprincal.UserPrinciple;
import com.codegym.luxstay.service.iservice.IRole;
import com.codegym.luxstay.service.iservice.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AuthControllerAPI {
    @Autowired
    IUser userService;
    @Autowired
    IRole roleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/sign-up")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpForm signUpForm){
        if(userService.existsByUsername(signUpForm.getUsername())){
            return new ResponseEntity<>(new ResponseMessage("no-user"), HttpStatus.OK);
        }
//        if(userService.existsByPhone(signUpForm.getPhone())){
//            return new ResponseEntity<>(new ResponseMessage("no-phone number"), HttpStatus.OK);
//        }
        User user = new User(signUpForm.getUsername(), passwordEncoder.encode(signUpForm.getPassword()),signUpForm.getPhone());
        Set<String> strRoles = signUpForm.getRoles();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role ->{
            switch (role){
                case "admin":
                    Role adminRole = roleService.findByName(RoleName.ADMIN).orElseThrow(()->
                            new RuntimeException("Role NOT FOUND"));
                    roles.add(adminRole);
                    break;
                case "pm":
                    Role pmRole = roleService.findByName(RoleName.PM).orElseThrow(
                            ()-> new RuntimeException("Role NOT FOUND"));
                    roles.add(pmRole);
                    break;
                default: Role userRole = roleService.findByName(RoleName.USER).orElseThrow(()->new RuntimeException("ROLE NOT FOUND"));
                    roles.add(userRole);
            }
        });
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<>(new ResponseMessage("Successful created!!!"), HttpStatus.CREATED);
    }
    @PostMapping("/sign-in")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok(
                new JwtResponse(token,
                        userPrinciple.getFullName(),
                        userPrinciple.getId(),
                        userPrinciple.getAvatar(),
                        userPrinciple.getAuthorities()));
    }
}
