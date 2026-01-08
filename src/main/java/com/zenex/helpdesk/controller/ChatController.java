package com.zenex.helpdesk.controller;

import com.zenex.helpdesk.model.ChatMessage;
import com.zenex.helpdesk.repo.ChatRepository;
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
    public void send(
            @RequestParam String employeeId,
            @RequestParam String message,
            @RequestParam(defaultValue = "USER") String sender
    ) {
        ChatMessage msg = new ChatMessage();
        msg.setEmployeeId(employeeId);
        msg.setMessage(message);
        msg.setSender(sender);
        msg.setCreatedAt(LocalDateTime.now());
        chatRepository.save(msg);
    }

    // USER + ADMIN read messages
    @GetMapping("/history/{employeeId}")
    public List<ChatMessage> history(@PathVariable String employeeId) {
        return chatRepository.findByEmployeeIdOrderByCreatedAtAsc(employeeId);
    }
}
