package com.healthcare.crm.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "billing")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Billing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ Each bill belongs to a patient
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    // ✅ Optional: bill linked to an appointment
    @OneToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    private Double amount;

    private String paymentStatus; // PENDING / PAID / CANCELLED

    private LocalDate billingDate;

    private String notes;
}
