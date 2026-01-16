package br.com.xuitter.xuitter_api.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Optional;

// @Entity - Indica que esta classe é uma entidade JPA mapeada para tabela no banco
@Entity
// @Table - Especifica o nome da tabela
@Table(name = "xuit")
public class Xuit {

    // @Id - Marca o campo como chave primária
    @Id
    // @GeneratedValue - Valor gerado automaticamente pelo banco (auto_increment)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @NotNull - Campo obrigatório
    // @Size - Tamanho mínimo 1 e máximo 42 caracteres
    @NotNull
    @Size(min = 1, max = 42)
    @Column(name = "content")
    private String content;

    // @Enumerated - Mapeia enum para coluna do banco
    // EnumType.STRING - Salva o nome do enum (ORIGINAL, REXUIT, QUOTE) ao invés do ordinal (0, 1, 2)
    @Enumerated(EnumType.STRING)
    @NotNull
    // @Column - Define o tipo da coluna como ENUM no MySQL
    @Column(columnDefinition = "enum('ORIGINAL', 'REXUIT', 'QUOTE')")
    private XuitType type;

    // @ManyToOne - Muitos Xuits pertencem a um User (relacionamento N:1)
    // optional = false - Campo obrigatório (não pode ser null)
    // fetch = FetchType.LAZY - Carrega o autor apenas quando acessado (performance)
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id") // Nome da coluna FK no banco
    private User author;

    // @ManyToOne - Muitos Xuits podem referenciar um Xuit original (relacionamento N:1)
    // optional = true (padrão) - Campo opcional (pode ser null para posts originais)
    // fetch = FetchType.LAZY - Carrega apenas quando acessado
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "original_xuit_id") // Nome da coluna FK no banco
    private Xuit originalXuit;

    // @Column - Mapeia para coluna created_at da tabela
    @NotNull
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Construtor padrão protegido - Exigido pelo JPA
    // @Deprecated - Indica que não deve ser usado diretamente
    @Deprecated
    protected Xuit() {}

    // Construtor para criar Xuit ORIGINAL
    public Xuit(String content, User author) {
        this.content = content;
        this.type = XuitType.ORIGINAL;
        this.author = author;
        this.createdAt = LocalDateTime.now();
    }

    // Construtor para criar REXUIT ou QUOTE
    public Xuit(String content, XuitType type, User author, Xuit originalXuit) {
        this.content = content;
        this.type = type;
        this.author = author;
        this.originalXuit = originalXuit;
        this.createdAt = LocalDateTime.now();
    }

    // Construtor para testes - permite definir ID manualmente
    public Xuit(Long id, String content, XuitType type, User author) {
        this.id = id;
        this.content = content;
        this.type = type;
        this.author = author;
        this.createdAt = LocalDateTime.now();
    }

    // Getter para ID
    public Long getId() {
        return id;
    }

    // Getter para content
    public String getContent() {
        return content;
    }

    // Getter para type
    public XuitType getType() {
        return type;
    }

    // Getter para author
    public User getAuthor() {
        return author;
    }

    // Getter para originalXuit
    // Retorna Optional para indicar que pode ser null (posts originais não têm originalXuit)
    public Optional<Xuit> getOriginalXuit() {
        return Optional.ofNullable(originalXuit);
    }

    // Getter para createdAt
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
