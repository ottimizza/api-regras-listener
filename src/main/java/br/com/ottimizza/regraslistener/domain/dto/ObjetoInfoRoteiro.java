package br.com.ottimizza.regraslistener.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder(toBuilder = true)
public class ObjetoInfoRoteiro {
    
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String idRoteiro;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cnpjEmpresa;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cnpjContabilidade;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String tipoLancamento;
}
