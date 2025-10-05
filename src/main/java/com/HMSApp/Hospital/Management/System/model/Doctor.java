package com.HMSApp.Hospital.Management.System.model;

import jakarta.persistence.*;

@Entity
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    private String specialization;
    
    @Column(unique = true)
    private String email;
    
    private String phone;
    private String department;

    // Constructors
    public Doctor() {}

    public Doctor(String name, String specialization, String email, String phone, String department) {
        this.name = name;
        this.specialization = specialization;
        this.email = email;
        this.phone = phone;
        this.department = department;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
}