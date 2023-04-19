package com.ronnytech.registry.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="t_children")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String registrationNumber;
    private String name;
    private String placeOfBirth;
    private LocalDate dateOfBirth;
    private String gender;
    private Double weight;
    private String motherName;
    private Integer motherAge;
    private String motherOccupation;
    
}
