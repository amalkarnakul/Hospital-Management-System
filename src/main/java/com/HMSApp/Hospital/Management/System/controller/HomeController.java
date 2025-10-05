package com.HMSApp.Hospital.Management.System.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Welcome to Hospital Management System! The application is running successfully.";
    }

    @GetMapping("/health")
    public String health() {
        return "Hospital Management System is up and running!";
    }

    @GetMapping("/api/status")
    public String apiStatus() {
        return "API is working correctly";
    }
}