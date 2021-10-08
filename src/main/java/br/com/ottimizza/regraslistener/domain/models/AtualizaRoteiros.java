package br.com.ottimizza.regraslistener.domain.models;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import br.com.ottimizza.regraslistener.domain.dto.ObjetoInfoRoteiro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder(toBuilder = true)
@NoArgsConstructor @AllArgsConstructor
@Table(name = "atualiza_roteiros")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class AtualizaRoteiros {
    
    @Id
    @Column(name = "id_roteiro", nullable = false)
    private String idRoteiro;

    @Type(type = "jsonb")
    @Column(name = "info_roteiros",columnDefinition = "jsonb")
    private ObjetoInfoRoteiro infoRoteiro;

    @Column(name = "exportado")
    private boolean exportado;
    
}
