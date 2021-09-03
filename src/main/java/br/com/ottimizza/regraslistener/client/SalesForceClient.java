package br.com.ottimizza.regraslistener.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.ottimizza.regraslistener.domain.dto.salesforce.SFParticularidade;

@FeignClient(name = "${salesforce.service.name}", url = "${salesforce.service.url}")
public interface SalesForceClient {
    
	@PostMapping("/api/v1/salesforce/listener/{chave}/composite/tree/Roteiros_vs_Contas__c")
	public ResponseEntity<String> upsertRegrasLote(@PathVariable("chave") String chave,
												   @RequestBody List<SFParticularidade> particularidades);

}
