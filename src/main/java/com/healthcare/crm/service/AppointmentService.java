package com.healthcare.crm.service;

import com.healthcare.crm.entity.*;
import com.healthcare.crm.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    // ✅ Create Appointment
    public Appointment createAppointment(Long patientId, Long doctorId, Appointment appointment) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setStatus(AppointmentStatus.SCHEDULED);

        return appointmentRepository.save(appointment);
    }

    // ✅ Get All Appointments
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // ✅ Get Appointment by ID
    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    // ✅ Update Appointment Status
    public Optional<Appointment> updateAppointmentStatus(Long id, AppointmentStatus status) {
        return appointmentRepository.findById(id).map(appt -> {
            appt.setStatus(status);
            return appointmentRepository.save(appt);
        });
    }

    // ✅ Delete Appointment
    public boolean deleteAppointment(Long id) {
        return appointmentRepository.findById(id).map(appt -> {
            appointmentRepository.delete(appt);
            return true;
        }).orElse(false);
    }
}
