package com.zenex.helpdesk.service;

import com.zenex.helpdesk.model.Ticket;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class PDFService {

    public byte[] generate(Ticket ticket) {

        if (ticket == null) {
            return "No ticket found".getBytes(StandardCharsets.UTF_8);
        }

        String content =
                "TICKET DETAILS\n\n" +
                "ID: " + ticket.getId() + "\n" +
                "Name: " + ticket.getName() + "\n" +
                "Email: " + ticket.getEmail() + "\n" +
                "Status: " + ticket.getStatus() + "\n\n" +
                "Description:\n" + ticket.getDescription();

        return content.getBytes(StandardCharsets.UTF_8);
    }
}
