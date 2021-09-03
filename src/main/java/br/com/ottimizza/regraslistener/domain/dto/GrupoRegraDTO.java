package br.com.ottimizza.regraslistener.domain.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder(toBuilder = true)
public class GrupoRegraDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private BigInteger id;

    private Integer posicao;
    
    private String contaMovimento;

    private Short tipoLancamento;

    private String idRoteiro;

    private String cnpjEmpresa;

    private String cnpjContabilidade;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataAtualizacao;
    
    private Integer contagemRegras;
    
    private Integer pesoRegras;

    private List<Regra> regras;

    private Boolean ativo;

    private String usuario;

    private List<String> camposRegras;
    
    private String contaPortador;
    
    private String contaDesconto;

    private String contaJuros;

    private String contaMulta;

}
