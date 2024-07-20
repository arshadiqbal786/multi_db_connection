package com.demo.service;



import com.demo.entity.primary.PrimaryEntity;
import com.demo.entity.secondary.SecondaryEntity;
import com.demo.repository.primary.PrimaryRepository;
import com.demo.repository.secondary.SecondaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyService {

    @Autowired
    private PrimaryRepository primaryRepository;

    @Autowired
    private SecondaryRepository secondaryRepository;

    public List<PrimaryEntity> getAllPrimaryEntities() {
        return primaryRepository.findAll();
    }

    public List<SecondaryEntity> getAllSecondaryEntities() {
        return secondaryRepository.findAll();
    }
}