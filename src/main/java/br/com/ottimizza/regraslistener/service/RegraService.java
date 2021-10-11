package br.com.ottimizza.regraslistener.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.ottimizza.regraslistener.client.IntegradorClient;
import br.com.ottimizza.regraslistener.client.SalesForceClient;
import br.com.ottimizza.regraslistener.domain.dto.GrupoRegraDTO;
import br.com.ottimizza.regraslistener.domain.dto.ObjetoInfoRoteiro;
import br.com.ottimizza.regraslistener.domain.dto.Regra;
import br.com.ottimizza.regraslistener.domain.dto.salesforce.SFParticularidade;
import br.com.ottimizza.regraslistener.domain.mapper.RegraMapper;
import br.com.ottimizza.regraslistener.domain.models.AtualizaRoteiros;
import br.com.ottimizza.regraslistener.repository.AtualizaRoteirosRepository;
import br.com.ottimizza.regraslistener.utils.MessageUtils;
import br.com.ottimizza.regraslistener.utils.StringUtils;

@Service
public class RegraService {
    
    @Autowired
    IntegradorClient integradorClient;

    @Autowired
    SalesForceClient salesForceClient;

    @Autowired 
    AtualizaRoteirosRepository atualizaRoteirosRepository;

    @Value("${chave-acesso-client}")
    private String CHAVE_FEIGN_CLIENT;
    
    public void exportarRegrasCrm(String message) throws Exception {
        List<String> ids = MessageUtils.listFromMessage(message);
        StringBuilder regrasCRM = new StringBuilder();
        List<SFParticularidade> particularidades = new ArrayList<>();
        String cnpjContabilidade = "";
        String cnpjEmpresa = "";
        String idRoteiro = "";
        String tipoLancamento = "";

        for(String idS : ids) {
            BigInteger id = BigInteger.valueOf(Integer.parseInt(idS));
            GrupoRegraDTO grupoRegra = integradorClient.getGrupoRegraPorId(CHAVE_FEIGN_CLIENT, id).getBody();
            List<Regra> regras = grupoRegra.getRegras();
            if(cnpjContabilidade.equals("")) {
                cnpjContabilidade = grupoRegra.getCnpjContabilidade();
                cnpjEmpresa = grupoRegra.getCnpjEmpresa();
                idRoteiro = grupoRegra.getIdRoteiro();
                tipoLancamento = grupoRegra.getTipoLancamento().toString();
            }

            int contador = 0;
            int idexRemove = -1;
            
            for(Regra regra : regras) {
                String campo = regra.getCampo();
                if(campo.contains("tipoMovimento")) idexRemove = contador;
                regra.setCampo(StringUtils.trataProSalesForce(campo, regra.getCondicao()));
                contador ++;
            }
            if(idexRemove != -1) regras.remove(idexRemove);
            grupoRegra.setRegras(regras);
            regrasCRM.append(RegraMapper.toSalesForce(grupoRegra, true).toString()+"#");
        }
        String objetoRegras = regrasCRM.toString().substring(0, regrasCRM.toString().lastIndexOf("#"));
        salesForceClient.upsertRegrasLote(CHAVE_FEIGN_CLIENT, objetoRegras);
        
        
       ObjetoInfoRoteiro infoRoteiro = ObjetoInfoRoteiro.builder()
                .cnpjEmpresa(cnpjEmpresa)
                .idRoteiro(idRoteiro)
                .tipoLancamento(tipoLancamento)
            .build();
        AtualizaRoteiros roteiro = AtualizaRoteiros.builder()
                .infoRoteiro(infoRoteiro)
                .exportado(false)
            .build();
        atualizaRoteirosRepository.save(roteiro);
    }
   

}
