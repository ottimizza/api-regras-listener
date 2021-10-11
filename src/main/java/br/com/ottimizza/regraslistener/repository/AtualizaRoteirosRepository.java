package br.com.ottimizza.regraslistener.repository;

import java.math.BigInteger;
import java.util.List;

import javax.jdo.annotations.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ottimizza.regraslistener.domain.models.AtualizaRoteiros;
import feign.Param;

@Repository
public interface AtualizaRoteirosRepository extends JpaRepository<AtualizaRoteiros, BigInteger> {
    
    @Query(value = "SELECT * FROM atualiza_roteiros  WHERE id_roteiro = :idRoteiro ",nativeQuery = true)
    AtualizaRoteiros buscaPorIdRoteiro(@Param("idRoteiro") String idRoteiro);

    @Modifying
	@Transactional
	@Query(value = "UPDATE atualiza_roteiros SET exportado = true WHERE id IN :exportados ",nativeQuery = true)
	void updateExportados(@Param("exportados") List<BigInteger> exportados) throws Exception;

}
