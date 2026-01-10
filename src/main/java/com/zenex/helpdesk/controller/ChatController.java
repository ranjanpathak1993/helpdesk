package com.zenex.helpdesk.controller;

import com.zenex.helpdesk.model.ChatMessage;
import com.zenex.helpdesk.repo.ChatRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatRepository chatRepository;

    public ChatController(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    // USER + ADMIN send messages
    @PostMapping("/send")
    public ResponseEntity<?> send(
            @RequestParam String employeeId,
            @RequestParam String message,
            @RequestParam(defaultValue = "USER") String sender
    ) {
        // Validate Fields
        if (employeeId == null || employeeId.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Employee ID is required");
        }

        if (message == null || message.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Message cannot be empty");
        }

        // Create model object
        ChatMessage msg = new ChatMessage();
        msg.setEmployeeId(employeeId.trim());
        msg.setMessage(message.trim());
        msg.setSender(sender.trim());
        msg.setCreatedAt(LocalDateTime.now());

        // Save to DB
        chatRepository.save(msg);

        return ResponseEntity.ok("Message Sent");
    }

    // USER + ADMIN read messages
    @GetMapping("/history/{employeeId}")
    public ResponseEntity<List<ChatMessage>> history(@PathVariable String employeeId) {

        if (employeeId == null || employeeId.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<ChatMessage> messages =
                chatRepository.findByEmployeeIdOrderByCreatedAtAsc(employeeId.trim());

        return ResponseEntity.ok(messages);
    }
}

