package com.zenex.helpdesk.repo;

import com.zenex.helpdesk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
