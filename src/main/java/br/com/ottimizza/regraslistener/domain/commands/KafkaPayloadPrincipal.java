package br.com.ottimizza.regraslistener.domain.commands;

import java.io.Serializable;
import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor @NoArgsConstructor
public class KafkaPayloadPrincipal implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

}