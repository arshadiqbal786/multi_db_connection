package com.demo.entity.primary;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "primary_table")
@Data
public class PrimaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


}

