package com.HMSApp.Hospital.Management.System.service;

import com.HMSApp.Hospital.Management.System.model.*;
import com.HMSApp.Hospital.Management.System.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HospitalService {

    @Autowired(required = false)
    private PatientRepository patientRepository;

    @Autowired(required = false)
    private DoctorRepository doctorRepository;

    @Autowired(required = false)
    private AppointmentRepository appointmentRepository;

    // Patient operations
    public List<Patient> getAllPatients() {
        if (patientRepository == null) {
            throw new RuntimeException("Database not available");
        }
        return patientRepository.findAll();
    }

    public Patient savePatient(Patient patient) {
        if (patientRepository == null) {
            throw new RuntimeException("Database not available");
        }
        return patientRepository.save(patient);
    }

    // Doctor operations
    public List<Doctor> getAllDoctors() {
        if (doctorRepository == null) {
            throw new RuntimeException("Database not available");
        }
        return doctorRepository.findAll();
    }

    public Doctor saveDoctor(Doctor doctor) {
        if (doctorRepository == null) {
            throw new RuntimeException("Database not available");
        }
        return doctorRepository.save(doctor);
    }

    // Appointment operations
    public List<Appointment> getAllAppointments() {
        if (appointmentRepository == null) {
            throw new RuntimeException("Database not available");
        }
        return appointmentRepository.findAllWithPatientAndDoctor();
    }

    public Appointment saveAppointment(Appointment appointment) {
        if (appointmentRepository == null) {
            throw new RuntimeException("Database not available");
        }
        return appointmentRepository.save(appointment);
    }

    // Dashboard data
    public List<DashboardData> getDashboardData() {
        if (appointmentRepository == null) {
            throw new RuntimeException("Database not available");
        }
        List<Appointment> appointments = appointmentRepository.findAllWithPatientAndDoctor();
        return appointments.stream()
                .map(appointment -> new DashboardData(
                        appointment.getPatient(),
                        appointment.getDoctor(),
                        appointment
                ))
                .collect(Collectors.toList());
    }

    // Initialize sample data
    public void initializeSampleData() {
        if (patientRepository == null || doctorRepository == null || appointmentRepository == null) {
            throw new RuntimeException("Database not available");
        }
        if (patientRepository.count() == 0) {
            // Create sample patients
            Patient patient1 = new Patient("John Doe", "john.doe@email.com", "+1-555-0101", "123 Main St, City", 35, "Male");
            Patient patient2 = new Patient("Jane Smith", "jane.smith@email.com", "+1-555-0102", "456 Oak Ave, City", 28, "Female");
            Patient patient3 = new Patient("Robert Johnson", "robert.j@email.com", "+1-555-0103", "789 Pine Rd, City", 42, "Male");
            
            patientRepository.saveAll(List.of(patient1, patient2, patient3));

            // Create sample doctors
            Doctor doctor1 = new Doctor("Dr. Sarah Connor", "Cardiology", "s.connor@hospital.com", "+1-555-0201", "Cardiology");
            Doctor doctor2 = new Doctor("Dr. James Wilson", "Neurology", "j.wilson@hospital.com", "+1-555-0202", "Neurology");
            Doctor doctor3 = new Doctor("Dr. Lisa House", "Pediatrics", "l.house@hospital.com", "+1-555-0203", "Pediatrics");
            
            doctorRepository.saveAll(List.of(doctor1, doctor2, doctor3));

            // Create sample appointments
            Appointment appointment1 = new Appointment(LocalDateTime.now().plusDays(1), "Regular checkup", "Scheduled", patient1, doctor1);
            Appointment appointment2 = new Appointment(LocalDateTime.now().plusDays(2), "Heart consultation", "Scheduled", patient2, doctor2);
            Appointment appointment3 = new Appointment(LocalDateTime.now().plusDays(3), "Neurological exam", "Scheduled", patient3, doctor3);
            
            appointmentRepository.saveAll(List.of(appointment1, appointment2, appointment3));
        }
    }
}