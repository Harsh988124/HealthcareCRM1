package com.healthcare.crm.controller;

import com.healthcare.crm.entity.*;
import com.healthcare.crm.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // ✅ Get all patients
    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> getAllPatients() {
        return ResponseEntity.ok(adminService.getAllPatients());
    }

    // ✅ Get all doctors
    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return ResponseEntity.ok(adminService.getAllDoctors());
    }

    // ✅ Approve doctor
    @PutMapping("/doctors/{id}/approve")
    public ResponseEntity<Doctor> approveDoctor(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.approveDoctor(id));
    }

    // ✅ Get all appointments
    @GetMapping("/appointments")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(adminService.getAllAppointments());
    }

    // ✅ Get all bills
    @GetMapping("/bills")
    public ResponseEntity<List<Billing>> getAllBills() {
        return ResponseEntity.ok(adminService.getAllBills());
    }
}
