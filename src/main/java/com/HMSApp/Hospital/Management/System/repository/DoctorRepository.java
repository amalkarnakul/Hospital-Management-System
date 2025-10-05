package com.HMSApp.Hospital.Management.System.repository;

import com.HMSApp.Hospital.Management.System.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByEmail(String email);
    List<Doctor> findBySpecialization(String specialization);
    List<Doctor> findByDepartment(String department);
}