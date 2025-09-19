package com.healthcare.crm.dto;

import lombok.Data;

@Data
public class PrescriptionRequest {
    private Long appointmentId;
    private String medication;
    private String dosage;
    private String instructions;
}
