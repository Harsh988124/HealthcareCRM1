package com.healthcare.crm.service;

import com.healthcare.crm.dto.PrescriptionRequest;
import com.healthcare.crm.entity.Appointment;
import com.healthcare.crm.entity.Prescription;
import com.healthcare.crm.repository.AppointmentRepository;
import com.healthcare.crm.repository.PrescriptionRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final AppointmentRepository appointmentRepository;

    // ✅ Create Prescription
    public Prescription createPrescription(PrescriptionRequest request) {
        Appointment appointment = appointmentRepository.findById(request.getAppointmentId())
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        Prescription prescription = Prescription.builder()
                .appointment(appointment)
                .medication(request.getMedication())
                .dosage(request.getDosage())
                .instructions(request.getInstructions())
                .build();

        return prescriptionRepository.save(prescription);
    }

    // ✅ Get All Prescriptions
    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    // ✅ Get Prescription by ID
    public Optional<Prescription> getPrescriptionById(Long id) {
        return prescriptionRepository.findById(id);
    }

    // ✅ Delete Prescription
    public boolean deletePrescription(Long id) {
        return prescriptionRepository.findById(id).map(p -> {
            prescriptionRepository.delete(p);
            return true;
        }).orElse(false);
    }

    // ✅ Generate Prescription PDF with QR Code
    // ✅ Generate Prescription PDF with QR Code
    // ✅ Generate Prescription PDF with Smart QR (Link to API)
    public byte[] generatePrescriptionPdf(Prescription prescription) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // 1. Create Smart QR Code (API link instead of plain text)
        String qrData = "http://localhost:8080/api/prescriptions/" + prescription.getId();
        BitMatrix matrix = new MultiFormatWriter().encode(qrData, BarcodeFormat.QR_CODE, 200, 200);
        ByteArrayOutputStream qrStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(matrix, "PNG", qrStream);

        // 2. Create PDF
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("📄 Prescription"));
        document.add(new Paragraph("Patient: " + prescription.getAppointment().getPatient().getName()));
        document.add(new Paragraph("Medication: " + prescription.getMedication()));
        document.add(new Paragraph("Dosage: " + prescription.getDosage()));
        document.add(new Paragraph("Instructions: " + prescription.getInstructions()));

        // Insert QR
        Image qrImage = new Image(ImageDataFactory.create(qrStream.toByteArray()));
        qrImage.setAutoScale(true);
        document.add(qrImage);

        // Footer hint
        document.add(new Paragraph("🔎 Scan the QR to verify this prescription online."));

        document.close();
        return baos.toByteArray();
    }


}
