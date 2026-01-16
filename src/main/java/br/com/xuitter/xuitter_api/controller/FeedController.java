package br.com.xuitter.xuitter_api.controller;

import br.com.xuitter.xuitter_api.dto.XuitResponse;
import br.com.xuitter.xuitter_api.repositories.XuitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

// @RestController - Indica que esta classe é um controller REST
// Combina @Controller + @ResponseBody (retorna dados JSON/texto diretamente)
@RestController
public class FeedController {

    // @Autowired - Injeta o repositório de xuits para buscar dados do banco
    @Autowired
    private XuitRepository xuitRepository;

    // @GetMapping - Mapeia requisições GET para o endpoint /feed
    // Este endpoint retorna o feed de xuits (posts) em formato JSON
    @GetMapping("/feed")
    public List<XuitResponse> feed() {
        // 1. Busca todos os xuits do banco de dados
        // 2. Converte cada Xuit (entidade) para XuitResponse (DTO)
        // 3. Coleta em uma lista
        // 4. Spring automaticamente serializa para JSON
        return xuitRepository.findAll()
                .stream()
                .map(XuitResponse::new) // Converte Xuit -> XuitResponse
                .collect(Collectors.toList());
    }
}
