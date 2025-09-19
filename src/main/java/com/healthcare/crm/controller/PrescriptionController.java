package com.healthcare.crm.controller;

import com.healthcare.crm.dto.PrescriptionRequest;
import com.healthcare.crm.entity.Prescription;
import com.healthcare.crm.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    // ✅ Create Prescription
    @PostMapping
    public ResponseEntity<Prescription> createPrescription(@RequestBody PrescriptionRequest request) {
        return ResponseEntity.ok(prescriptionService.createPrescription(request));
    }

    // ✅ Get All Prescriptions
    @GetMapping
    public ResponseEntity<List<Prescription>> getAllPrescriptions() {
        return ResponseEntity.ok(prescriptionService.getAllPrescriptions());
    }

    // ✅ Get Prescription by ID
    @GetMapping("/{id}")
    public ResponseEntity<Prescription> getPrescriptionById(@PathVariable Long id) {
        return prescriptionService.getPrescriptionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Delete Prescription
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrescription(@PathVariable Long id) {
        return prescriptionService.deletePrescription(id) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

    // ✅ Download Prescription as PDF with QR
    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> downloadPrescriptionPdf(@PathVariable Long id) {
        return (ResponseEntity<byte[]>) prescriptionService.getPrescriptionById(id)
                .map(prescription -> {
                    try {
                        byte[] pdf = prescriptionService.generatePrescriptionPdf(prescription);
                        return ResponseEntity.ok()
                                .header("Content-Disposition", "attachment; filename=prescription_" + id + ".pdf")
                                .header("Content-Type", "application/pdf")
                                .body(pdf);
                    } catch (Exception e) {
                        return ResponseEntity.<byte[]>internalServerError().build();
                    }
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}
