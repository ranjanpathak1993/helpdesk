package com.zenex.helpdesk.controller;

import com.zenex.helpdesk.model.Ticket;
import com.zenex.helpdesk.repo.TicketRepository;
import com.zenex.helpdesk.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketRepository ticketRepo;

    @Autowired
    private EmailService emailService;   // ✅ ADDED

    private final String UPLOAD_DIR = "uploads/";

    @GetMapping("/new")
    public String newTicket() {
        return "ticket-form";
    }

    @PostMapping("/submit")
    public String submitTicket(
            @ModelAttribute Ticket ticket,
            @RequestParam(required = false) MultipartFile image,
            @RequestParam(required = false) MultipartFile video
    ) throws Exception {

        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) dir.mkdirs();

        if (image != null && !image.isEmpty()) {
            String img = UUID.randomUUID() + "_" + image.getOriginalFilename();
            Files.write(Paths.get(UPLOAD_DIR + img), image.getBytes());
            ticket.setImagePath(img);
        }

        if (video != null && !video.isEmpty()) {
            String vid = UUID.randomUUID() + "_" + video.getOriginalFilename();
            Files.write(Paths.get(UPLOAD_DIR + vid), video.getBytes());
            ticket.setVideoPath(vid);
        }

        Ticket saved = ticketRepo.save(ticket);

        // ✅ EMAIL ALERT
        emailService.sendTicketConfirmation(saved);

        return "redirect:/ticket-success/" + saved.getId();
    }
}
