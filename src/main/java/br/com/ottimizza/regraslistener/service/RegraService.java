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
import br.com.ottimizza.regraslistener.domain.dto.HistoricoDTO;
import br.com.ottimizza.regraslistener.domain.dto.ObjetoInfoRoteiro;
import br.com.ottimizza.regraslistener.domain.dto.Regra;
import br.com.ottimizza.regraslistener.domain.mapper.HistoricoMapper;
import br.com.ottimizza.regraslistener.domain.mapper.RegraMapper;
import br.com.ottimizza.regraslistener.domain.models.AtualizaRoteiros;
import br.com.ottimizza.regraslistener.repository.AtualizaRoteirosRepository;
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
        String messageString = message.substring(message.indexOf("message\":\""), message.lastIndexOf("\""));
        messageString = messageString.substring(messageString.indexOf("{"), messageString.lastIndexOf("}")+ 1);
        String gpString = messageString.substring(messageString.indexOf("{") + 1, messageString.indexOf("@")-1);
        String hsString = messageString.substring(messageString.indexOf("@") + 2, messageString.lastIndexOf("}"));
        String[] grupoRegrasIds = {};
        String[] historicosIds = {};

        if(gpString.contains(",")) {
            grupoRegrasIds = gpString.split(",");
        }
        else {
            if(gpString != null && !gpString.equals(""))
                grupoRegrasIds = new String[]{gpString};
        }
        if(hsString.contains(",")) {
            historicosIds = hsString.split(",");
        }
        else {
            if(hsString != null && !hsString.equals(""))
                historicosIds = new String[]{hsString};
        }
        StringBuilder regrasCRM = new StringBuilder();
        StringBuilder historicosCRM = new StringBuilder();
        String cnpjContabilidade = "";
        String cnpjEmpresa = "";
        String idRoteiro = "";
        String tipoLancamento = "";

        try {
            if(grupoRegrasIds.length > 0) {
                for(String idS : grupoRegrasIds) {
                    if(idS != null && !idS.equals("")) {
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
                        try { 
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
                        catch (Exception exR) {
                            System.out.println("Error regra id: "+id);
                            continue;
                        }
                    }
                }
                String objetoRegras = "";
                if(regrasCRM.toString().contains("#"))
                    objetoRegras = regrasCRM.toString().substring(0, regrasCRM.toString().lastIndexOf("#"));
                salesForceClient.upsertRegrasLote(CHAVE_FEIGN_CLIENT, objetoRegras);
            }
        }
        catch(Exception exGp) {
            System.out.println("Error exporting rules -> "+exGp.getMessage()+" -> "+gpString);
        }

        try {
            if(historicosIds.length > 0) {
                for(String idH : historicosIds) {
                    if(idH != null && !idH.equals("")){
                        BigInteger id = BigInteger.valueOf(Integer.parseInt(idH));
                        HistoricoDTO historico = integradorClient.getHistoricoPorId(CHAVE_FEIGN_CLIENT, id).getBody();
                        historicosCRM.append(HistoricoMapper.toSalesForce(historico).toString()+"#");
                    }
                }
                String objetoHistoricos = "";
                if(historicosCRM.toString().contains("#"))
                    objetoHistoricos = historicosCRM.toString().substring(0, historicosCRM.toString().lastIndexOf("#"));
                salesForceClient.upsertHistoricosLote(CHAVE_FEIGN_CLIENT, objetoHistoricos);
            }
        }
        catch(Exception exH) {
            System.out.println("Error exporting historical -> "+exH.getMessage()+" -> "+hsString);
        }
        ObjetoInfoRoteiro infoRoteiro = ObjetoInfoRoteiro.builder()
                .cnpjEmpresa(cnpjEmpresa)
                .cnpjContabilidade(cnpjContabilidade)
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
