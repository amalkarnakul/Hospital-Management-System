package com.HMSApp.Hospital.Management.System;

import com.HMSApp.Hospital.Management.System.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HospitalManagementSystemApplication implements CommandLineRunner {

    @Autowired(required = false)
    private HospitalService hospitalService;

    public static void main(String[] args) {
        SpringApplication.run(HospitalManagementSystemApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("🏥 Hospital Management System started successfully!");
        
        if (hospitalService != null) {
            try {
                hospitalService.initializeSampleData();
                System.out.println("✅ Sample data initialized in database.");
            } catch (Exception e) {
                System.out.println("⚠️ Database not available: " + e.getMessage());
                System.out.println("🔄 Application will use mock data.");
            }
        } else {
            System.out.println("📊 Running with mock data (database disabled).");
        }
        
        System.out.println("🌐 Backend API available at: http://localhost:8082");
        System.out.println("📱 Frontend should be at: http://localhost:4200");
    }
}