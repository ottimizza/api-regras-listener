package br.com.ottimizza.regraslistener.client;

import java.math.BigInteger;
import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.ottimizza.regraslistener.domain.dto.GrupoRegraDTO;


@FeignClient(name = "${integrador.service.name}", url = "${integrador.service.url}")
public interface IntegradorClient {
    
    @GetMapping("/api/v1/regras/listener/{chave}")
    ResponseEntity<List<BigInteger>> getGrupoRegrasId(@PathVariable("chave") String chave,
                                                      @Valid String cnpjEmpresa,
                                                      @Valid String cnpjContabilidade,
                                                      @Valid Short tipoMovimento);

    @GetMapping("/api/v1/regras/listener/{chave}/{id}")
    ResponseEntity<GrupoRegraDTO> getGrupoRegraPorId(@PathVariable("chave") String chave,
                                                     @PathVariable("id") BigInteger id);

}
