package com.zenex.helpdesk.controller;

import com.zenex.helpdesk.service.AiHelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
public class AiHelpController {

    @Autowired
    private AiHelpService aiHelpService;

    // Chat endpoint
    @PostMapping("/chat")
    public String chat(@RequestParam String message) {
        return aiHelpService.getReply(message);
    }
}
