package br.com.ottimizza.regraslistener.client;

import java.math.BigInteger;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.ottimizza.regraslistener.domain.dto.GrupoRegraDTO;
import br.com.ottimizza.regraslistener.domain.dto.HistoricoDTO;


@FeignClient(name = "${integrador.service.name}", url = "${integrador.service.url}")
public interface IntegradorClient {

    @GetMapping("/integra/v1/regras/{id}")
    ResponseEntity<GrupoRegraDTO> getGrupoRegraPorId(@RequestHeader("Authorization") String chave,
                                                     @PathVariable("id") BigInteger id);

    @GetMapping("/integra/v1/regras/historico/{id}")
    ResponseEntity<HistoricoDTO> getHistoricoPorId(@RequestHeader("Authorization") String chave,
                                                     @PathVariable("id") BigInteger id);

}
