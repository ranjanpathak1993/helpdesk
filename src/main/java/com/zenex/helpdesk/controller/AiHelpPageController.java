package com.zenex.helpdesk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AiHelpPageController {

    @GetMapping("/ai-help")
    public String aiHelpPage() {
        return "ai-help";
    }
}
