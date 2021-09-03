package br.com.ottimizza.regraslistener.utils;

public class StringUtils {
    
    public static String trataProSalesForce(String campo, Short condicao) {
		
		if (campo.contains("complemento")) {
			campo = campo.replaceFirst("c", "C").replace("o0", "o (0").concat(")");
		}
		else if (campo.contains("tipoPlanilha")) {
			campo = "Tipo Planilha";
		}
		else if (campo.contains("portador")) {
			campo = "Portador";
		}
		else if (campo.contains("descricao")) {
			campo = "Fornecedor/Cliente";
		}
		else if (campo.contains("documento")) {
			campo = "Documento/NF";
		}
		else if(campo.contains("nomeArquivo")) {
			campo = "Nome do Arquivo";
		}
		
		//TRATANDO CONDICAO 
		
		if(condicao == 1) {
			campo = campo.concat(" contém");
		}
		else if(condicao == 2) {
			campo = campo.concat(" não contém");
		}
		else if(condicao == 3) {
			campo = campo.concat(" começa com");
		}
		else if(condicao == 4) {
			campo = campo.concat(" igual a");
		}
		return campo;
		
	}
    
}
