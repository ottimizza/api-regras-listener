package br.com.ottimizza.regraslistener.domain.mapper;

import br.com.ottimizza.regraslistener.domain.dto.HistoricoDTO;
import br.com.ottimizza.regraslistener.domain.dto.salesforce.SFHistorico;
import br.com.ottimizza.regraslistener.utils.StringUtils;

public class HistoricoMapper {
    
    public static SFHistorico toSalesForce(HistoricoDTO historico) {
    	String codigoHistorico = "";
    	
    	String texto01 = "";
    	String campo01 = "";
    	
    	String texto02 = "";
    	String campo02 = "";
    	
    	String texto03 = "";
    	String campo03 = "";
    	
    	String texto04 = "";
    	String campo04 = "";
    	
    	String texto05 = "";
    	String campo05 = "";
    	
    	String[] tudo = historico.getHistorico().split("\\$");
    	codigoHistorico = tudo[0].replace("CodigoHistorico:", "").trim();
    	
    	texto01 = tudo[1].trim();
    	campo01 = tudo[2].substring(tudo[2].indexOf("{")+1,tudo[2].indexOf("}"));
		
    	texto02 = tudo[2].substring(tudo[2].indexOf("}")+1);
    	campo02 = tudo[3].substring(tudo[3].indexOf("{")+1,tudo[3].indexOf("}"));
		
    	texto03 = tudo[3].substring(tudo[3].indexOf("}")+1);
    	campo03 = tudo[4].substring(tudo[4].indexOf("{")+1,tudo[4].indexOf("}"));
		
    	texto04 = tudo[4].substring(tudo[4].indexOf("}")+1);
    	campo04 = tudo[5].substring(tudo[5].indexOf("{")+1,tudo[5].indexOf("}"));
		
    	texto05 = tudo[5].substring(tudo[5].indexOf("}")+1);
    	campo05 = tudo[6].substring(tudo[6].indexOf("{")+1,tudo[6].indexOf("}"));
    	
    	
    	return SFHistorico.builder()
    			.RecordTypeId(SFHistorico.RECORD_TYPE_ID)
				.ID_Externo__c(historico.getId().toString())
    			.Roteiro__c(historico.getIdRoteiro())
    			.Codigo_Historico__c(codigoHistorico)
    			.Se_Campo__c("Conta Normal - Movimento - igual a ")
    			.O_texto_01__c(historico.getContaMovimento())
    			.Texto_01__c(texto01)
    			.Campo_01__c(StringUtils.trataCampoHistoricoProSalesForce(campo01))
    			.Texto_02__c(texto02)
    			.Campo_02__c(StringUtils.trataCampoHistoricoProSalesForce(campo02))
    			.Texto_03__c(texto03)
    			.Campo_03__c(StringUtils.trataCampoHistoricoProSalesForce(campo03))
    			.Texto_04__c(texto04)
    			.Campo_04__c(StringUtils.trataCampoHistoricoProSalesForce(campo04))
    			.Texto_05__c(texto05)
    			.Campo_05__c(StringUtils.trataCampoHistoricoProSalesForce(campo05))
    		.build();
    }

}
