package com.HMSApp.Hospital.Management.System.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("supabase")
public class DatabaseConfig {
    // This configuration is active only when supabase profile is active
    // and database connection is successful
}