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

    // USER -> ADMIN + USER (Real-time)
    @MessageMapping("/chat.send")
    public void sendUserMessage(@Payload ChatMessage msg) {
        msg.setCreatedAt(LocalDateTime.now());
        chatRepository.save(msg);

        messagingTemplate.convertAndSend("/topic/chat/" + msg.getEmployeeId(), msg);
        messagingTemplate.convertAndSend("/topic/admin-alert", msg);
    }

    // ADMIN -> USER (Real-time)
    @MessageMapping("/chat.reply")
    public void sendAdminReply(@Payload ChatMessage msg) {
        msg.setCreatedAt(LocalDateTime.now());
        chatRepository.save(msg);

        messagingTemplate.convertAndSend("/topic/chat/" + msg.getEmployeeId(), msg);
    }
}
