package com.zenex.helpdesk.controller;

import com.zenex.helpdesk.model.ChatMessage;
import com.zenex.helpdesk.repo.ChatRepository;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class LiveChatSocketController {

    private final ChatRepository chatRepository;

    public LiveChatSocketController(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public ChatMessage handleMessage(ChatMessage msg) {

        msg.setCreatedAt(LocalDateTime.now());

        // Save into DB
        chatRepository.save(msg);

        // Return → broadcast message to all clients
        return msg;
    }
}

