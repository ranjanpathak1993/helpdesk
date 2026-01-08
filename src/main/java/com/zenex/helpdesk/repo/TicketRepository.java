package com.zenex.helpdesk.repo;

import com.zenex.helpdesk.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    // ===== STATUS COUNTS =====
    long countByStatus(String status);

    // ===== DATE RANGE REPORT =====
    List<Ticket> findByCreatedTimeBetween(
            LocalDateTime from,
            LocalDateTime to
    );

    // ❌ REMOVED: findByPriority(...)
    // ❌ REMOVED: any method using non-existing fields
}
