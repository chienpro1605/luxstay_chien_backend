package com.codegym.luxstay.service.impl;

import com.codegym.luxstay.model.Image;
import com.codegym.luxstay.repository.IImageRepository;
import com.codegym.luxstay.service.iservice.IImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;
@Service

public class ImageServiceImpl implements IImage {
    @Autowired
    IImageRepository iImageRepository;

    @Override
    public Iterable<Image> findAll() {
        return iImageRepository.findAll();
    }

    @Override
    public Optional<Image> findById(long id) {
        return iImageRepository.findById(id);
    }

    @Override
    public Image save(Image image) {
        return iImageRepository.save(image);
    }

    @Override
    public void delete(long id) {
        iImageRepository.deleteById(id);
    }
}
