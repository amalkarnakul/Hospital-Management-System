package com.HMSApp.Hospital.Management.System.repository;

import com.HMSApp.Hospital.Management.System.model.Appointment;
import com.HMSApp.Hospital.Management.System.model.Doctor;
import com.HMSApp.Hospital.Management.System.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPatient(Patient patient);
    List<Appointment> findByDoctor(Doctor doctor);
    List<Appointment> findByStatus(String status);
    List<Appointment> findByAppointmentDateBetween(LocalDateTime start, LocalDateTime end);
    
    @Query("SELECT a FROM Appointment a JOIN FETCH a.patient JOIN FETCH a.doctor ORDER BY a.appointmentDate DESC")
    List<Appointment> findAllWithPatientAndDoctor();
}