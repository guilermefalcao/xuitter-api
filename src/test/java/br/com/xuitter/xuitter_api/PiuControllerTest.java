package br.com.xuitter.xuitter_api;

import br.com.xuitter.xuitter_api.controller.PiuController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

// Importações necessárias para os métodos de teste
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @WebMvcTest - Testa apenas a camada web, sem carregar o contexto completo (não precisa do banco)
@WebMvcTest(PiuController.class)
public class PiuControllerTest {

    // @Autowired - Injeta automaticamente a dependência do MockMvc
    @Autowired
    private MockMvc mockMvc; // MockMvc simula requisições HTTP para testar controllers

    @Test
    @DisplayName("should piu") // Nome descritivo do teste
    void t1() throws Exception {
        // Testa o endpoint GET /piu
        mockMvc.perform(get("/piu")) // Executa uma requisição GET para /piu
               .andExpect(status().isOk()) // Verifica se o status HTTP é 200 (OK)
               .andExpect(content().string("🐦")); // Verifica se o conteúdo retornado é o emoji do pássaro
    }
}