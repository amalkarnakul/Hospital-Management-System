package com.HMSApp.Hospital.Management.System.model;

public class DashboardData {
    private Patient patient;
    private Doctor doctor;
    private Appointment appointment;

    // Constructors
    public DashboardData() {}

    public DashboardData(Patient patient, Doctor doctor, Appointment appointment) {
        this.patient = patient;
        this.doctor = doctor;
        this.appointment = appointment;
    }

    // Getters and Setters
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }

    public Appointment getAppointment() { return appointment; }
    public void setAppointment(Appointment appointment) { this.appointment = appointment; }
}