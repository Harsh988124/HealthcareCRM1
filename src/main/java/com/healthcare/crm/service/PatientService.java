package com.healthcare.crm.service;

import com.healthcare.crm.entity.Patient;
import com.healthcare.crm.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    // Add a new patient
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    // Get all patients
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // Get one patient by ID
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    // Update patient
    public Patient updatePatient(Long id, Patient updatedPatient) {
        return patientRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedPatient.getName());
                    existing.setEmail(updatedPatient.getEmail());
                    existing.setPhone(updatedPatient.getPhone());
                    existing.setAge(updatedPatient.getAge());
                    existing.setGender(updatedPatient.getGender());
                    existing.setAddress(updatedPatient.getAddress());
                    return patientRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    // Delete patient
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
