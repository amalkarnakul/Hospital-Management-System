package com.HMSApp.Hospital.Management.System.controller;

import com.HMSApp.Hospital.Management.System.model.*;
import com.HMSApp.Hospital.Management.System.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/hospital")
@CrossOrigin(origins = "http://localhost:4200")
public class HospitalController {

    @Autowired(required = false)
    private HospitalService hospitalService;

    @GetMapping("/status")
    public String getStatus() {
        if (hospitalService != null) {
            return "✅ Connected to Supabase Database";
        }
        return "⚠️ Using Mock Data (Database not connected)";
    }

    @GetMapping("/patients")
    public List<Patient> getPatients() {
        if (hospitalService != null) {
            try {
                List<Patient> patients = hospitalService.getAllPatients();
                System.out.println("✅ Retrieved " + patients.size() + " patients from database");
                return patients;
            } catch (Exception e) {
                System.out.println("❌ Database error: " + e.getMessage());
                System.out.println("🔄 Falling back to mock data");
                return getMockPatients();
            }
        }
        System.out.println("⚠️ HospitalService is null, using mock data");
        return getMockPatients();
    }

    @PostMapping("/patients")
    public Patient createPatient(@RequestBody Patient patient) {
        if (hospitalService != null) {
            return hospitalService.savePatient(patient);
        }
        // Mock response when database is not configured
        patient.setId(System.currentTimeMillis());
        return patient;
    }

    @GetMapping("/doctors")
    public List<Doctor> getDoctors() {
        if (hospitalService != null) {
            return hospitalService.getAllDoctors();
        }
        // Return mock data when database is not configured
        return getMockDoctors();
    }

    @PostMapping("/doctors")
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        if (hospitalService != null) {
            return hospitalService.saveDoctor(doctor);
        }
        // Mock response when database is not configured
        doctor.setId(System.currentTimeMillis());
        return doctor;
    }

    @GetMapping("/appointments")
    public List<Appointment> getAppointments() {
        if (hospitalService != null) {
            return hospitalService.getAllAppointments();
        }
        // Return mock data when database is not configured
        return getMockAppointments();
    }

    @PostMapping("/appointments")
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        if (hospitalService != null) {
            return hospitalService.saveAppointment(appointment);
        }
        // Mock response when database is not configured
        appointment.setId(System.currentTimeMillis());
        return appointment;
    }

    @GetMapping("/dashboard-data")
    public List<DashboardData> getDashboardData() {
        System.out.println("🔍 Dashboard data requested");
        if (hospitalService != null) {
            try {
                List<DashboardData> data = hospitalService.getDashboardData();
                System.out.println("✅ Retrieved " + data.size() + " dashboard items from database");
                return data;
            } catch (Exception e) {
                System.out.println("❌ Database error: " + e.getMessage());
                System.out.println("🔄 Falling back to mock dashboard data");
                return getMockDashboardData();
            }
        }
        System.out.println("⚠️ HospitalService is null, using mock dashboard data");
        List<DashboardData> mockData = getMockDashboardData();
        System.out.println("📊 Returning " + mockData.size() + " mock dashboard items");
        return mockData;
    }

    @PostMapping("/initialize-data")
    public String initializeData() {
        if (hospitalService != null) {
            hospitalService.initializeSampleData();
            return "Sample data initialized successfully in Supabase!";
        }
        return "Database not configured. Using mock data.";
    }

    // Mock data methods
    private List<Patient> getMockPatients() {
        Patient p1 = new Patient("John Doe", "john.doe@email.com", "+1-555-0101", "123 Main St, City", 35, "Male");
        p1.setId(1L);
        Patient p2 = new Patient("Jane Smith", "jane.smith@email.com", "+1-555-0102", "456 Oak Ave, City", 28,
                "Female");
        p2.setId(2L);
        Patient p3 = new Patient("Robert Johnson", "robert.j@email.com", "+1-555-0103", "789 Pine Rd, City", 42,
                "Male");
        p3.setId(3L);
        return Arrays.asList(p1, p2, p3);
    }

    private List<Doctor> getMockDoctors() {
        Doctor d1 = new Doctor("Dr. Sarah Connor", "Cardiology", "s.connor@hospital.com", "+1-555-0201", "Cardiology");
        d1.setId(1L);
        Doctor d2 = new Doctor("Dr. James Wilson", "Neurology", "j.wilson@hospital.com", "+1-555-0202", "Neurology");
        d2.setId(2L);
        Doctor d3 = new Doctor("Dr. Lisa House", "Pediatrics", "l.house@hospital.com", "+1-555-0203", "Pediatrics");
        d3.setId(3L);
        return Arrays.asList(d1, d2, d3);
    }

    private List<Appointment> getMockAppointments() {
        List<Patient> patients = getMockPatients();
        List<Doctor> doctors = getMockDoctors();

        Appointment a1 = new Appointment(LocalDateTime.now().plusDays(1), "Regular checkup", "Scheduled",
                patients.get(0), doctors.get(0));
        a1.setId(1L);
        Appointment a2 = new Appointment(LocalDateTime.now().plusDays(2), "Heart consultation", "Scheduled",
                patients.get(1), doctors.get(1));
        a2.setId(2L);
        Appointment a3 = new Appointment(LocalDateTime.now().plusDays(3), "Neurological exam", "Scheduled",
                patients.get(2), doctors.get(2));
        a3.setId(3L);
        return Arrays.asList(a1, a2, a3);
    }

    private List<DashboardData> getMockDashboardData() {
        List<Patient> patients = getMockPatients();
        List<Doctor> doctors = getMockDoctors();
        List<Appointment> appointments = getMockAppointments();

        return Arrays.asList(
                new DashboardData(patients.get(0), doctors.get(0), appointments.get(0)),
                new DashboardData(patients.get(1), doctors.get(1), appointments.get(1)),
                new DashboardData(patients.get(2), doctors.get(2), appointments.get(2)));
    }
}