package br.com.xuitter.xuitter_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController - Indica que esta classe é um controller REST
@RestController
public class PiuController {

    // @GetMapping - Mapeia requisições GET para o endpoint /piu
    @GetMapping("/piu")
    public String piu() {
        return "🐦"; // Retorna o emoji do pássaro
    }
}