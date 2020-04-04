package br.com.cep.repository;

import br.com.cep.model.Cep;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Interface para persistência de dados ao banco.
 *
 * @author renan.santos
 */
public interface CepRepository extends JpaRepository<Cep, String> {

    Optional<Cep> findById(String id);

    @Query(value = "select c.cep from TCep c where c.ibge in (select d.ibge "
            + "from TCidade d where d.ibge = :ibge_p and d.uf = :uf_p)", nativeQuery = true)
    Optional<List<String>> findCepsByIBGEandUF(@Param("ibge_p") String ibge_p,
            @Param("uf_p") String uf_p);
}
