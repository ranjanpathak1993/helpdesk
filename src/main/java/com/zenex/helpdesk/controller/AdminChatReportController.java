package com.zenex.helpdesk.controller;

import com.zenex.helpdesk.model.ChatMessage;
import com.zenex.helpdesk.repo.ChatRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminChatReportController {

    private final ChatRepository repo;

    public AdminChatReportController(ChatRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/chat-reports")
    public String page() {
        return "admin/admin-chat-reports";
    }

    @ResponseBody
    @GetMapping("/chat-users")
    public List<String> users() {
        return repo.findDistinctEmployeeId();
    }

    @ResponseBody
    @GetMapping("/chat-history/{emp}")
    public List<ChatMessage> history(@PathVariable String emp) {
        return repo.findByEmployeeIdOrderByCreatedAtAsc(emp);
    }
}
