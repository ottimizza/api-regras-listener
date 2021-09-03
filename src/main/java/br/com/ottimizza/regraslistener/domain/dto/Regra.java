package br.com.ottimizza.regraslistener.domain.dto;

import java.io.Serializable;
import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder(toBuilder = true)
public class Regra implements Serializable {
    
    private BigInteger id;

    private String campo;

    private Short condicao;

    private String valor;

    public static class Condicao {

        public static final int CONTEM = 1;

        public static final int NAO_CONTEM = 2;

        public static final int COMECAO_COM = 3;

        public static final int IGUAL = 4;

    }

}
