package com.zenex.helpdesk.repo;

import com.zenex.helpdesk.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findByEmployeeIdOrderByCreatedAtAsc(String employeeId);

    @Query("SELECT DISTINCT c.employeeId FROM ChatMessage c WHERE c.employeeId IS NOT NULL")
    List<String> findDistinctEmployeeId();
}
