package com.codegym.luxstay.controller;

import com.codegym.luxstay.model.User;
import com.codegym.luxstay.service.iservice.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private IUser userService;


    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser (@PathVariable Long id){
        Optional<User> userOptional = userService.findById(id);
        if(!userOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Iterable<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> findById(@PathVariable long id) {
        Optional<User> selected = userService.findById(id);
        if(selected.isPresent()) {
            return new ResponseEntity<>(selected,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateInfo (@PathVariable Long id, @RequestBody User user){
        Optional<User> selected = userService.findById(id);
        if(!selected.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user.setId(selected.get().getId());
        user.setUsername(selected.get().getUsername());
        user.setPassword(selected.get().getPassword());
        user.setRoles(selected.get().getRoles());
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
