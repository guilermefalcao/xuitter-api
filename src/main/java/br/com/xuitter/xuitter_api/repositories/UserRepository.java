package br.com.xuitter.xuitter_api.repositories;

import br.com.xuitter.xuitter_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

// Interface de repositório para a entidade User
// JpaRepository<User, Long> - User é a entidade, Long é o tipo da chave primária (id)
// Spring Data JPA cria automaticamente a implementação com métodos CRUD:
// - save(user) - Salvar/atualizar
// - findById(id) - Buscar por ID
// - findAll() - Buscar todos
// - deleteById(id) - Deletar por ID
// - count() - Contar registros
// E muitos outros métodos prontos
public interface UserRepository extends JpaRepository<User, Long> {
    // Não precisa implementar nada!
    // Spring Data JPA gera automaticamente a implementação
}
