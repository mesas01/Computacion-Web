package com.example.tallerwiki.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tallerwiki.model.User;
import com.example.tallerwiki.service.UserService;

@Controller
public class IndexController {
    @Autowired
    UserService userService;

    // Mostrar la página index
    @GetMapping({"/", "/index.html"})
    public String index() {
        return "index";
    }

    // Recibir datos del formulario

    @PostMapping("/contacto")
public String enviarFormulario(@RequestParam("nombres") String nombres,
                              @RequestParam("apellidos") String apellidos,
                              @RequestParam("correo") String correo,
                              @RequestParam("semestre") String semestre,
                              @RequestParam("mensaje") String mensaje) {
    User usuario = new User(nombres, apellidos, correo, semestre, mensaje);
    System.out.println(nombres + apellidos + correo + semestre + mensaje);
    userService.add(usuario);
    return "redirect:/index.html";  // Redirige a página index después de guardar
}
}
