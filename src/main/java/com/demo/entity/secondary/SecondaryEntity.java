package com.demo.entity.secondary;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "secondary_table")
@Data
public class SecondaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    // Getters and setters
}
