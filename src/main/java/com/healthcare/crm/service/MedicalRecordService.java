package com.healthcare.crm.service;

import com.healthcare.crm.dto.MedicalRecordRequest;
import com.healthcare.crm.entity.Appointment;
import com.healthcare.crm.entity.MedicalRecord;
import com.healthcare.crm.entity.Patient;
import com.healthcare.crm.repository.AppointmentRepository;
import com.healthcare.crm.repository.MedicalRecordRepository;
import com.healthcare.crm.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;

    // ✅ Create Medical Record using DTO
    public MedicalRecord createRecord(MedicalRecordRequest request) {
        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Appointment appointment = null;
        if (request.getAppointmentId() != null) {
            appointment = appointmentRepository.findById(request.getAppointmentId())
                    .orElseThrow(() -> new RuntimeException("Appointment not found"));
        }

        MedicalRecord record = MedicalRecord.builder()
                .patient(patient)
                .appointment(appointment)
                .diagnosis(request.getDiagnosis())
                .treatment(request.getTreatment())
                .notes(request.getNotes())
                .recordDate(request.getRecordDate())
                .build();

        return medicalRecordRepository.save(record);
    }

    public List<MedicalRecord> getAllRecords() {
        return medicalRecordRepository.findAll();
    }

    public List<MedicalRecord> getRecordsByPatient(Long patientId) {
        return medicalRecordRepository.findByPatientId(patientId);
    }

    public Optional<MedicalRecord> getRecordById(Long id) {
        return medicalRecordRepository.findById(id);
    }

    public boolean deleteRecord(Long id) {
        return medicalRecordRepository.findById(id).map(record -> {
            medicalRecordRepository.delete(record);
            return true;
        }).orElse(false);
    }
}
