package com.zenex.helpdesk.config;

import com.zenex.helpdesk.model.Admin;
import com.zenex.helpdesk.repo.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminSeeder implements CommandLineRunner {

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void run(String... args) {

        Admin admin = adminRepo.findByUsername("admin");

        if (admin == null) {
            admin = new Admin();
            admin.setUsername("admin");
            admin.setPassword(encoder.encode("admin"));
            adminRepo.save(admin);
            System.out.println("✅ Admin created (admin/admin)");
        } else {
            admin.setPassword(encoder.encode("admin"));
            adminRepo.save(admin);
            System.out.println("♻ Admin password reset to admin");
        }
    }
}
