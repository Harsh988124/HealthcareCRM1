package com.healthcare.crm.service;

import com.healthcare.crm.entity.Doctor;
import com.healthcare.crm.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    // ✅ Create Doctor
    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // ✅ Get All Doctors
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // ✅ Get Doctor by ID
    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    // ✅ Update Doctor
    public Optional<Doctor> updateDoctor(Long id, Doctor updatedDoctor) {
        return doctorRepository.findById(id).map(doctor -> {
            doctor.setName(updatedDoctor.getName());
            doctor.setEmail(updatedDoctor.getEmail());
            doctor.setPhone(updatedDoctor.getPhone());
            doctor.setSpecialization(updatedDoctor.getSpecialization());
            doctor.setExperienceYears(updatedDoctor.getExperienceYears());
            doctor.setHospitalName(updatedDoctor.getHospitalName());
            return doctorRepository.save(doctor);
        });
    }

    // ✅ Delete Doctor
    public boolean deleteDoctor(Long id) {
        return doctorRepository.findById(id).map(doctor -> {
            doctorRepository.delete(doctor);
            return true;
        }).orElse(false);
    }
}
