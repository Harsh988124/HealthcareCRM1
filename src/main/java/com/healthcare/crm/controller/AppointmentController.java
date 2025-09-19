package com.healthcare.crm.controller;

import com.healthcare.crm.dto.AppointmentRequest;
import com.healthcare.crm.entity.*;
import com.healthcare.crm.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;




    // ✅ Get All Appointments
    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    // ✅ Get Appointment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Update Appointment Status
    @PutMapping("/{id}/status")
    public ResponseEntity<Appointment> updateStatus(
            @PathVariable Long id,
            @RequestParam AppointmentStatus status) {
        return appointmentService.updateAppointmentStatus(id, status)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Delete Appointment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        return appointmentService.deleteAppointment(id) ?
                ResponseEntity.noContent().<Void>build() :
                ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody AppointmentRequest request) {
        Appointment appointment = Appointment.builder()
                .appointmentDate(request.getAppointmentDate())
                .reason(request.getReason())
                .build();

        return ResponseEntity.ok(
                appointmentService.createAppointment(request.getPatientId(), request.getDoctorId(), appointment)
        );
    }

}
