package br.com.xuitter.xuitter_api;

import br.com.xuitter.xuitter_api.entities.User;
import br.com.xuitter.xuitter_api.entities.Xuit;
import br.com.xuitter.xuitter_api.entities.XuitType;
import br.com.xuitter.xuitter_api.repositories.UserRepository;
import br.com.xuitter.xuitter_api.repositories.XuitRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

// Importações necessárias para os métodos de teste
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;

// @SpringBootTest - Carrega o contexto completo da aplicação (precisa do banco rodando)
@SpringBootTest
// @AutoConfigureMockMvc - Configura o MockMvc para testes de controllers
@AutoConfigureMockMvc
// @ActiveProfiles - Ativa o perfil "test" para usar application-test.properties
@ActiveProfiles("test")
public class FeedControllerTest {

    // @Autowired - Injeta o MockMvc para simular requisições HTTP
    @Autowired
    private MockMvc mockMvc;

    // @Autowired - Injeta o repositório de usuários para criar dados de teste
    @Autowired
    private UserRepository userRepository;

    // @Autowired - Injeta o repositório de xuits para criar dados de teste
    @Autowired
    private XuitRepository xuitRepository;

    // TESTE QUE VAI FALHAR - Propositalmente para demonstrar TDD (Test-Driven Development)
    // Motivos da falha:
    // 1. FeedController retorna string vazia ("") ao invés de JSON
    // 2. Não busca xuits do banco de dados
    // 3. Campo "authorUsername" não existe na entidade Xuit (só tem "author")
    // 4. Não há DTO (Data Transfer Object) para formatar a resposta
    // 5. Não converte entidades para JSON com estrutura esperada
    @Test
    @DisplayName("should return a feed of xuits")
    void t1() throws Exception {
        // Cria um usuário de teste no banco
        User fabricio = userRepository.save(new User("fabricio"));

        // Cria uma lista de 3 xuits de teste e salva no banco
        // NÃO definimos ID manualmente - deixamos o banco gerar automaticamente
        List.of(
            new Xuit("siga o ia sob controle", fabricio),
            new Xuit("siga o xuitterrr  11", fabricio),
            new Xuit("siga o xuitterrr  22", fabricio)
        ).forEach(xuitRepository::save);   // Para cada um vai salvar no banco

        // Testa o endpoint GET /feed
        mockMvc.perform(get("/feed")) // Executa uma requisição GET para /feed
                // Verifica se retorna status 200 (OK)
                .andExpect(status().isOk())
                
                // Verifica se o array JSON tem 3 elementos
                .andExpect(jsonPath("$", hasSize(3)))
                
                // Validações do primeiro xuit (id 1)
                .andExpect(jsonPath("$[0].id").isNotEmpty())
                .andExpect(jsonPath("$[0].content").value("siga o ia sob controle"))
                .andExpect(jsonPath("$[0].authorUsername").value("fabricio"))
                .andExpect(jsonPath("$[0].type").value("ORIGINAL"))
                
                // Validações do segundo xuit (id 2)
                .andExpect(jsonPath("$[1].id").isNotEmpty())
                .andExpect(jsonPath("$[1].content").value("siga o xuitterrr  11"))
                .andExpect(jsonPath("$[1].authorUsername").value("fabricio"))
                .andExpect(jsonPath("$[1].type").value("ORIGINAL"))
                
                // Validações do terceiro xuit (id 3)
                .andExpect(jsonPath("$[2].id").isNotEmpty())
                .andExpect(jsonPath("$[2].content").value("siga o xuitterrr  22"))
                .andExpect(jsonPath("$[2].authorUsername").value("fabricio"))
                .andExpect(jsonPath("$[2].type").value("ORIGINAL"));
    }

    // TESTE QUE PASSA - Verifica apenas se o endpoint responde com status 200
    // Este teste passa porque FeedController já tem o método feed() que retorna status 200
    @Test
    @DisplayName("should return status 200 when accessing feed")
    void t2() throws Exception {
        // Testa apenas se o endpoint /feed está acessível e retorna 200 OK
        mockMvc.perform(get("/feed"))
                .andExpect(status().isOk());
    }
}