package com.example.tallerwiki;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    // Mapea la raíz del sitio (http://localhost:8080/) al index.html
    @GetMapping("/")
    public String index() {
        return "index"; // Devuelve el nombre del archivo HTML sin la extensión .html
    }

    // Mapea http://localhost:8080/index.html también
    @GetMapping("/index.html")
    public String home() {
        return "index";
    }

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