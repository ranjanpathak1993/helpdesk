package com.zenex.helpdesk.controller;

import com.zenex.helpdesk.model.Ticket;
import com.zenex.helpdesk.repo.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @PreAuthorize("hasAuthority('DASHBOARD_VIEW')")
@GetMapping("/admin/dashboard")
public String dashboard() {
    return "admin/dashboard";
}
    @Autowired
    private TicketRepository ticketRepo;

    // ================= DASHBOARD =================
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("totalTickets", ticketRepo.count());
        model.addAttribute("openTickets", ticketRepo.countByStatus("OPEN"));
        model.addAttribute("progressTickets", ticketRepo.countByStatus("IN_PROGRESS"));
        model.addAttribute("closedTickets", ticketRepo.countByStatus("CLOSED"));
        return "admin/admin-dashboard";
    }

    // ================= TICKETS LIST =================
    @GetMapping("/tickets")
    public String tickets(Model model) {
        model.addAttribute("tickets", ticketRepo.findAll());
        return "admin/admin-tickets";
    }

    // ================= UPDATE STATUS =================
    @PostMapping("/tickets/update-status")
    public String updateStatus(
            @RequestParam Long id,
            @RequestParam String status) {

        ticketRepo.findById(id).ifPresent(ticket -> {
            ticket.setStatus(status);
            ticketRepo.save(ticket);
        });

        return "redirect:/admin/tickets";
    }

    // ================= SINGLE TICKET VIEW =================
    @GetMapping("/tickets/{id}")
    public String viewTicket(@PathVariable Long id, Model model) {
        Ticket ticket = ticketRepo.findById(id).orElse(null);
        model.addAttribute("ticket", ticket);
        return "admin/admin-ticket-view";
    }

    // ================= REPORTS =================
    @GetMapping("/reports")
    public String reports() {
        return "admin/admin-reports";
    }

    // ================= ANALYTICS =================
    @GetMapping("/analytics")
    public String analytics() {
        return "admin/admin-analytics";
    }
}
