package br.com.ottimizza.regraslistener.domain.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("message")
    private String message;
    
    @JsonProperty("status")
    private String status;

    @JsonProperty("record")
    private T record;

    @JsonProperty("records")
    private List<T> records;

    public GenericResponse(List<T> records) {
        this.records = records;
    }

    public GenericResponse(T record) {
        this.record = record;
    }

    public GenericResponse(String message) {
        this.message = message;
    }
}
