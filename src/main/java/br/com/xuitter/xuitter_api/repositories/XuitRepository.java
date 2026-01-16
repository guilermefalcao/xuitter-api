package br.com.xuitter.xuitter_api.repositories;

import br.com.xuitter.xuitter_api.entities.Xuit;
import org.springframework.data.jpa.repository.JpaRepository;

// Interface de repositório para a entidade Xuit
// JpaRepository<Xuit, Long> - Xuit é a entidade, Long é o tipo da chave primária (id)
// Spring Data JPA cria automaticamente a implementação com métodos CRUD:
// - save(xuit) - Salvar/atualizar xuit
// - findById(id) - Buscar xuit por ID
// - findAll() - Buscar todos os xuits
// - deleteById(id) - Deletar xuit por ID
// - count() - Contar xuits
// E muitos outros métodos prontos
public interface XuitRepository extends JpaRepository<Xuit, Long> {
    // Não precisa implementar nada!
    // Spring Data JPA gera automaticamente a implementação
    
    // Você pode adicionar métodos customizados seguindo convenções de nomenclatura:
    // List<Xuit> findByAuthor(User author);
    // List<Xuit> findByType(XuitType type);
    // List<Xuit> findByAuthorOrderByCreatedAtDesc(User author);
}
