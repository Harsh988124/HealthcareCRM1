package com.healthcare.crm.service;

import com.healthcare.crm.entity.*;
import com.healthcare.crm.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    private final BillingRepository billingRepository;

    // ✅ Manage Patients
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // ✅ Manage Doctors
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // ✅ Approve Doctor
    public Doctor approveDoctor(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        doctor.setApproved(true); // assume we add "approved" field
        return doctorRepository.save(doctor);
    }

    // ✅ Manage Appointments
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // ✅ Manage Billing
    public List<Billing> getAllBills() {
        return billingRepository.findAll();
    }
}
