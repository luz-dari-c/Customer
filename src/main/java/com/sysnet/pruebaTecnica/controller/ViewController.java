package com.sysnet.pruebaTecnica.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/clientes")
    public String clientes() {
        return "clientes";
    }

}