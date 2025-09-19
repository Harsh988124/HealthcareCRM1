package com.healthcare.crm.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "patients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)   // required field
    private String name;

    @Column(unique = true, nullable = false)  // unique patient email
    private String email;

    @Column(unique = true, nullable = false)  // unique phone
    private String phone;

    private int age;

    private String gender;  // Male, Female, Other

    private String address;
}
