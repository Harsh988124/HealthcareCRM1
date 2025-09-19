package com.healthcare.crm.service;

import com.healthcare.crm.dto.BillingRequest;
import com.healthcare.crm.entity.*;
import com.healthcare.crm.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BillingService {

    private final BillingRepository billingRepository;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;

    // ✅ Create Billing
    public Billing createBilling(BillingRequest request) {
        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Appointment appointment = null;
        if (request.getAppointmentId() != null) {
            appointment = appointmentRepository.findById(request.getAppointmentId())
                    .orElseThrow(() -> new RuntimeException("Appointment not found"));
        }

        Billing billing = Billing.builder()
                .patient(patient)
                .appointment(appointment)
                .amount(request.getAmount())
                .paymentStatus(request.getPaymentStatus())
                .billingDate(request.getBillingDate())
                .notes(request.getNotes())
                .build();

        return billingRepository.save(billing);
    }

    // ✅ Get all billings
    public List<Billing> getAllBillings() {
        return billingRepository.findAll();
    }

    // ✅ Get billing by ID
    public Optional<Billing> getBillingById(Long id) {
        return billingRepository.findById(id);
    }

    // ✅ Delete billing
    public boolean deleteBilling(Long id) {
        return billingRepository.findById(id).map(bill -> {
            billingRepository.delete(bill);
            return true;
        }).orElse(false);
    }
}
