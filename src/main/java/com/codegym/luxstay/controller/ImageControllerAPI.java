package com.codegym.luxstay.controller;

import com.codegym.luxstay.model.Apartment;
import com.codegym.luxstay.model.Image;
import com.codegym.luxstay.service.impl.ApartmentServiceImpl;
import com.codegym.luxstay.service.impl.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("api/images")
public class ImageControllerAPI {
    @Autowired
    ImageServiceImpl imageService;

    @GetMapping("")
    public ResponseEntity<Iterable<Image>> getAll() {
        return new ResponseEntity<>(imageService.findAll(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Image> save(@RequestBody Image image) {
        return new ResponseEntity<>(imageService.save(image), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Image> findById(@PathVariable Long id) {
        Optional<Image> imageOptional = imageService.findById(id);
        if(!imageOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(imageOptional.get(),HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Image> editById(@PathVariable Long id,@RequestBody Image image) {
        Optional<Image> imageOptional = imageService.findById(id);
        if(!imageOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        image.setId(imageOptional.get().getId());
        imageService.save(image);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Image> deleteById(@PathVariable Long id) {
        Optional<Image>  imageOptional = imageService.findById(id);
        if(!imageOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        imageService.delete(id);
        return  new ResponseEntity<>(HttpStatus.OK);
    }
}
