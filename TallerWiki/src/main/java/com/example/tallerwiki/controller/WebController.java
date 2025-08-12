package com.example.tallerwiki.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/architecture.html")
    public String architecture() {
        return "architecture";
    }

    @GetMapping("/deployment.html")
    public String deployment() {
        return "deployment";
    }

    @GetMapping("/development.html")
    public String development() {
        return "development";
    }

    @GetMapping("/requirements.html")
    public String requirements() {
        return "requirements";
    }

    @GetMapping("/testing.html")
    public String testing() {
        return "testing";
    }

    // agregar más mapeos para otras páginas aquí
}