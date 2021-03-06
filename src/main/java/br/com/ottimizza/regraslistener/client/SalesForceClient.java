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
    
	@PostMapping("integra/v1/salesforce/composite/tree/Roteiros_vs_Contas__c")
	public ResponseEntity<String> upsertRegrasLote(@RequestHeader("Authorization") String chave,
												   @RequestBody String particularidades);

	@PostMapping("integra/v1/salesforce/composite/tree/Roteiro_vs_Historio__c")
	public ResponseEntity<String> upsertHistoricosLote(@RequestHeader("Authorization") String chave,
														@RequestBody String historicos);
											   


}
