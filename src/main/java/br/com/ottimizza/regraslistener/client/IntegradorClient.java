package br.com.ottimizza.regraslistener.client;

import java.math.BigInteger;
import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.ottimizza.regraslistener.domain.dto.GrupoRegraDTO;


@FeignClient(name = "${integrador.service.name}", url = "${integrador.service.url}")
public interface IntegradorClient {

    @GetMapping("/integra/v1/regras/{id}")
    ResponseEntity<GrupoRegraDTO> getGrupoRegraPorId(@RequestHeader("Authorization") String chave,
                                                     @PathVariable("id") BigInteger id);

}
