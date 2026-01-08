package com.zenex.helpdesk.service;

import com.zenex.helpdesk.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendTicketConfirmation(Ticket ticket) {

        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(ticket.getEmail());
            msg.setSubject("🎫 Ticket Created | Zenex Helpdesk");

            msg.setText(
                    "Hello " + ticket.getName() + ",\n\n" +
                    "Your support ticket has been successfully created.\n\n" +
                    "Ticket ID: " + ticket.getId() + "\n" +
                    "Issue Type: " + ticket.getIssueType() + "\n" +
                    "Priority: " + ticket.getPriority() + "\n" +
                    "Status: " + ticket.getStatus() + "\n\n" +
                    "Our support team will contact you soon.\n\n" +
                    "Regards,\n" +
                    "Zenex IT Helpdesk"
            );

            mailSender.send(msg);

        } catch (Exception e) {
            System.out.println("❌ EMAIL FAILED: " + e.getMessage());
        }
    }
}
