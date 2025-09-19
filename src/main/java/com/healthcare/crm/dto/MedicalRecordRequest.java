package com.healthcare.crm.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MedicalRecordRequest {
    private Long patientId;
    private Long appointmentId; // optional
    private String diagnosis;
    private String treatment;
    private String notes;
    private LocalDate recordDate;
}
