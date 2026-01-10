package com.zenex.helpdesk.controller;

import com.zenex.helpdesk.model.ChatMessage;
import com.zenex.helpdesk.repo.ChatRepository;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class LiveChatSocketController {

    private final ChatRepository chatRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public LiveChatSocketController(ChatRepository chatRepository, SimpMessagingTemplate messagingTemplate) {
        this.chatRepository = chatRepository;
        this.messagingTemplate = messagingTemplate;
    }

    // USER sends → ADMIN receives
    @MessageMapping("/chat.send")
    public void handleUserMessage(@Payload ChatMessage msg) {

        msg.setCreatedAt(LocalDateTime.now());
        chatRepository.save(msg);

        // send message to admin and user specific channel
        messagingTemplate.convertAndSend("/topic/chat/" + msg.getEmployeeId(), msg);

        // admin should know new chat user
        messagingTemplate.convertAndSend("/topic/admin-alert", msg);
    }

    // ADMIN replies → USER receives
    @MessageMapping("/chat.reply")
    public void handleAdminReply(@Payload ChatMessage msg) {

        msg.setCreatedAt(LocalDateTime.now());
        chatRepository.save(msg);

        // send reply to that specific user
        messagingTemplate.convertAndSend("/topic/chat/" + msg.getEmployeeId(), msg);
    }
}
