package com.zenex.helpdesk.controller;

import com.zenex.helpdesk.model.Ticket;
import com.zenex.helpdesk.repo.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PageController {

    @Autowired
    private TicketRepository ticketRepo;

    // ===== TRACK FORM =====
    @GetMapping("/track")
    public String trackPage() {
        return "track";
    }

    // ===== TRACK RESULT (GET, NO POST) =====
    @GetMapping("/track/result")
    public String trackResult(@RequestParam("id") String id, Model model) {

        Long ticketId;
        try {
            ticketId = Long.parseLong(id.trim());
        } catch (Exception e) {
            model.addAttribute("error", "Invalid Ticket ID");
            return "track";
        }

        Ticket ticket = ticketRepo.findById(ticketId).orElse(null);

        if (ticket == null) {
            model.addAttribute("error", "Ticket not found");
            return "track";
        }

        model.addAttribute("ticket", ticket);
        return "track-result";
    }
}

