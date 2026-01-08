package com.zenex.helpdesk.controller;

import com.zenex.helpdesk.model.Ticket;
import com.zenex.helpdesk.repo.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TicketSuccessController {

    @Autowired
    private TicketRepository ticketRepo;

    @GetMapping("/ticket-success/{id}")
    public String success(@PathVariable Long id, Model model) {

        Ticket ticket = ticketRepo.findById(id).orElse(null);

        if (ticket == null) {
            return "redirect:/";
        }

        model.addAttribute("ticket", ticket);
        return "ticket-success";
    }
}
