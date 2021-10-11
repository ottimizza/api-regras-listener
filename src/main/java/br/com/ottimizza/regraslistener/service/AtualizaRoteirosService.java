package br.com.ottimizza.regraslistener.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ottimizza.regraslistener.repository.AtualizaRoteirosRepository;

@Service
public class AtualizaRoteirosService {
 
    @Autowired
    AtualizaRoteirosRepository repository;

    public String updateExportados(List<BigInteger> idsRoteiros) throws Exception {
		StringBuilder retorno = new StringBuilder();
        try {
			repository.updateExportados(idsRoteiros);
            retorno.append("{\"status\":\"Success\",");
            retorno.append("\"message\":\"Item atualizado com sucesso!\"}");
		} catch (Exception e) {
            System.out.println(e.getMessage());
            retorno.append("{\"status\":\"Error\",");
            retorno.append("\"message\":\"Houve um problema ao atualizar!\"}");
			return retorno.toString();
		}
		return retorno.toString();
	}

}
