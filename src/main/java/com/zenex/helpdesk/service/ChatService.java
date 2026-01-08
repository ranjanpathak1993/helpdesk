package com.zenex.helpdesk.service;

import com.zenex.helpdesk.model.ChatMessage;
import com.zenex.helpdesk.repo.ChatRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService {

    private final ChatRepository repo;

    public ChatService(ChatRepository repo) {
        this.repo = repo;
    }

    public void save(String employeeId, String message, String sender) {
        ChatMessage cm = new ChatMessage();
        cm.setEmployeeId(employeeId);
        cm.setMessage(message);
        cm.setSender(sender);
        cm.setCreatedAt(LocalDateTime.now());
        repo.save(cm);
    }

    public List<ChatMessage> history(String employeeId) {
        return repo.findByEmployeeIdOrderByCreatedAtAsc(employeeId);
    }

    public List<String> users() {
        return repo.findDistinctEmployeeId();
    }
}
