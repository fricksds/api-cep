package br.com.cep.repository;

import br.com.cep.model.Cep;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface para persistência de dados ao banco.
 *
 * @author renan.santos
 */
public interface CepRepository extends JpaRepository<Cep, String> {

    Optional<Cep> findById(String id);
}
