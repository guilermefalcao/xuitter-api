package br.com.xuitter.xuitter_api.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

// @Entity - Indica que esta classe é uma entidade JPA mapeada para tabela no banco
@Entity
// @Table - Especifica o nome da tabela (opcional se nome da classe = nome da tabela)
@Table(name = "user")
public class User {

    // @Id - Marca o campo como chave primária
    @Id
    // @GeneratedValue - Valor gerado automaticamente pelo banco (auto_increment)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Pattern - Valida que username contém apenas letras, números e underscore
    @Pattern(regexp = "\\w+")
    // @NotBlank - Não pode ser null, vazio ou só espaços
    @NotBlank
    // @Size - Tamanho máximo de 10 caracteres (conforme tabela)
    @Size(max = 10)
    // @Column - Mapeia para coluna específica (opcional se nome igual)
    @Column(name = "username")
    private String username;

    // @NotNull - Campo obrigatório (não pode ser null)
    @NotNull
    // @Column - Mapeia para coluna created_at da tabela
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Construtor padrão protegido - Exigido pelo JPA
    // @Deprecated - Indica que não deve ser usado diretamente
    @Deprecated
    protected User() {}

    // Construtor público para criar novos usuários
    public User(String username) {
        this.username = username;
        this.createdAt = LocalDateTime.now(); // Define data/hora atual
    }

    // Getter para ID
    public Long getId() {
        return id;
    }

    // Getter para username
    public String getUsername() {
        return username;
    }

    // Getter para createdAt
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
