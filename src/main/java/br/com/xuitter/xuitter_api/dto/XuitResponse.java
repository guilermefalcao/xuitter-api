package br.com.xuitter.xuitter_api.dto;

import br.com.xuitter.xuitter_api.entities.Xuit;
import com.fasterxml.jackson.annotation.JsonProperty;

// DTO (Data Transfer Object) - Objeto usado para transferir dados entre camadas
// Representa a estrutura JSON que será retornada pela API
public class XuitResponse {

    // @JsonProperty - Anotação do Jackson para serialização JSON
    // Garante que o campo será incluído no JSON mesmo sendo final
    @JsonProperty
    private final Long id;

    @JsonProperty
    private final String content;

    @JsonProperty
    private final String type; // String ao invés de XuitType (serializa como texto)

    @JsonProperty
    private final String authorUsername; // Nome do autor (extraído de User.username)

    // Construtor que converte entidade Xuit para DTO XuitResponse
    public XuitResponse(Xuit xuit) {
        this.id = xuit.getId();
        this.content = xuit.getContent();
        this.type = xuit.getType().name(); // Converte enum para String ("ORIGINAL", "REXUIT", "QUOTE")
        this.authorUsername = xuit.getAuthor().getUsername(); // Extrai username do autor
    }

    // Getters - Necessários para serialização JSON pelo Spring
    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getType() {
        return type;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }
}
