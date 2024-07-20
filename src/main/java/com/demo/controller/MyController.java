package com.demo.controller;

import com.demo.entity.primary.PrimaryEntity;
import com.demo.entity.secondary.SecondaryEntity;
import com.demo.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyController {

    @Autowired
    private MyService myService;

    @GetMapping("/primary")
    public List<PrimaryEntity> getAllPrimaryEntities() {
        return myService.getAllPrimaryEntities();
    }

    @GetMapping("/secondary")
    public List<SecondaryEntity> getAllSecondaryEntities() {
        return myService.getAllSecondaryEntities();
    }
}
