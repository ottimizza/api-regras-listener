package br.com.ottimizza.regraslistener.domain.mapper;

import java.util.List;

import br.com.ottimizza.regraslistener.domain.dto.GrupoRegraDTO;
import br.com.ottimizza.regraslistener.domain.dto.Regra;
import br.com.ottimizza.regraslistener.domain.dto.salesforce.SFParticularidade;

public class RegraMapper {

    public static SFParticularidade toSalesForce(GrupoRegraDTO grupoRegra, boolean comIdExterno) {
    	String sequenciaRegras = "500";
		String contaDesconto = "";
		String contaJuros = "";
		String contaMulta = "";
		String contaPortador = "";
    	
    	List<Regra> regras = grupoRegra.getRegras();
    	String e01 = ""; 
    	String txt02 = "";	
    	
    	String e02 = ""; 
    	String txt03 = "";	
    	
    	String e03 = ""; 
    	String txt04 = "";	
    	
    	String e04 = ""; 
    	String txt05 = "";	
    	
    	if(regras.size() > 1) {
    		e01   = regras.get(1).getCampo();
    		txt02 = regras.get(1).getValor();
    		sequenciaRegras = "520";
    	}
    	if(regras.size() > 2) {
    		e02   = regras.get(2).getCampo();
    		txt03 = regras.get(2).getValor();
    		sequenciaRegras = "530";
    	}
    	if(regras.size() > 3 ) {
    		e03   = regras.get(3).getCampo();
    		txt04 =	regras.get(3).getValor();
    		sequenciaRegras = "540";
    	}
    	if(regras.size() > 4) {
    		e04   = regras.get(4).getCampo();
    		txt05 = regras.get(4).getValor();
    		sequenciaRegras = "550";
    	}
    	
		if(grupoRegra.getContaDesconto() != null && !grupoRegra.getContaDesconto().equals(""))
			contaDesconto = grupoRegra.getContaDesconto();

		if(grupoRegra.getContaJuros() != null && !grupoRegra.getContaJuros().equals(""))
			contaJuros = grupoRegra.getContaJuros();
			
		if(grupoRegra.getContaMulta() != null && !grupoRegra.getContaMulta().equals(""))
			contaMulta = grupoRegra.getContaMulta();

		if(grupoRegra.getContaPortador() != null && !grupoRegra.getContaPortador().equals(""))
			contaPortador = grupoRegra.getContaPortador();

    	SFParticularidade s = SFParticularidade.builder()
    			.RecordTypeId(SFParticularidade.RECORD_TYPE_ID)
    			.Roteiro__c(grupoRegra.getIdRoteiro())
    			.Conta_Movimento__c(grupoRegra.getContaMovimento())
    			.Se_Campo__c(regras.get(0).getCampo())
    			.O_texto_01__c(regras.get(0).getValor())
    			.E_01__c(e01)
    			.O_texto_02__c(txt02)
    			.E_02__c(e02)
    			.O_texto_03__c(txt03)
    			.E_03__c(e03)
    			.O_texto_04__c(txt04)
    			.E_04__c(e04)
    			.O_texto_05__c(txt05)
    			.Sequencia_das_Regras__c(sequenciaRegras)
    			.Ordem_OUD__c(grupoRegra.getPosicao())
				.Conta_Desconto__c(contaDesconto)
				.Conta_Juros__c(contaJuros)
				.Conta_Multa__c(contaMulta)
				.Conta_Portador__c(contaPortador)
    		.build();
    	
    	if(comIdExterno) s.setID_Externo__c(grupoRegra.getId().toString());
    	
    	return s;
    }
    
    public static SFParticularidade toSalesForce(GrupoRegraDTO grupoRegra) {
    	return toSalesForce(grupoRegra, false);
    }

    public static SFParticularidade toSalesForce(GrupoRegraDTO grupoRegra, List<Regra> regras, boolean comIdExterno) {
    	String sequenciaRegras = "500";
		String contaDesconto = "";
		String contaJuros = "";
		String contaMulta = "";
		String contaPortador = "";
    	
    	String e01 = ""; 
    	String txt02 = "";	
    	
    	String e02 = ""; 
    	String txt03 = "";	
    	
    	String e03 = ""; 
    	String txt04 = "";	
    	
    	String e04 = ""; 
    	String txt05 = "";	
    	
    	if(regras.size() > 1) {
    		e01   = regras.get(1).getCampo();
    		txt02 = regras.get(1).getValor();
    		sequenciaRegras = "520";
    	}
    	if(regras.size() > 2) {
    		e02   = regras.get(2).getCampo();
    		txt03 = regras.get(2).getValor();
    		sequenciaRegras = "530";
    	}
    	if(regras.size() > 3 ) {
    		e03   = regras.get(3).getCampo();
    		txt04 =	regras.get(3).getValor(); 
    		sequenciaRegras = "540";
    	}
    	if(regras.size() > 4) {
    		e04   = regras.get(4).getCampo();
    		txt05 = regras.get(4).getValor();
    		sequenciaRegras = "550";
    	}
    	
    	if(grupoRegra.getContaDesconto() != null && !grupoRegra.getContaDesconto().equals(""))
			contaDesconto = grupoRegra.getContaDesconto();

		if(grupoRegra.getContaJuros() != null && !grupoRegra.getContaJuros().equals(""))
			contaJuros = grupoRegra.getContaJuros();
			
		if(grupoRegra.getContaMulta() != null && !grupoRegra.getContaMulta().equals(""))
			contaMulta = grupoRegra.getContaMulta();

		if(grupoRegra.getContaPortador() != null && !grupoRegra.getContaPortador().equals(""))
			contaPortador = grupoRegra.getContaPortador();

    	SFParticularidade s = SFParticularidade.builder()
    			.RecordTypeId(SFParticularidade.RECORD_TYPE_ID)
    			.Roteiro__c(grupoRegra.getIdRoteiro())
    			.Conta_Movimento__c(grupoRegra.getContaMovimento())
    			.Se_Campo__c(regras.get(0).getCampo())
    			.O_texto_01__c(regras.get(0).getValor())
    			.E_01__c(e01)
    			.O_texto_02__c(txt02)
    			.E_02__c(e02)
    			.O_texto_03__c(txt03)
    			.E_03__c(e03)
    			.O_texto_04__c(txt04)
    			.E_04__c(e04)
    			.O_texto_05__c(txt05)
    			.Sequencia_das_Regras__c(sequenciaRegras)
    			.Ordem_OUD__c(grupoRegra.getPosicao())
				.Conta_Desconto__c(contaDesconto)
				.Conta_Juros__c(contaJuros)
				.Conta_Multa__c(contaMulta)
				.Conta_Portador__c(contaPortador)
    		.build();
    	
    	if(comIdExterno) s.setID_Externo__c(grupoRegra.getId().toString());
    	
    	return s;
    }
    
}
