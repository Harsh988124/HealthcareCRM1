package com.healthcare.crm.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BillingRequest {
    private Long patientId;
    private Long appointmentId;  // optional
    private Double amount;
    private String paymentStatus;
    private LocalDate billingDate;
    private String notes;
}
