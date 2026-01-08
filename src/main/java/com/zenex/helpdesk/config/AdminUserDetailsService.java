package com.zenex.helpdesk.config;

import com.zenex.helpdesk.model.Admin;
import com.zenex.helpdesk.repo.AdminRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;

    public AdminUserDetailsService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Admin admin = adminRepository.findByUsername(username);

        if (admin == null) {
            throw new UsernameNotFoundException("Admin not found");
        }

        return admin;
    }
}
