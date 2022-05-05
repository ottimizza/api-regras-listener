package br.com.ottimizza.regraslistener.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.com.ottimizza.regraslistener.config.kafka.KafkaProperties;
import br.com.ottimizza.regraslistener.service.RegraService;

@Component
public class KafkaConsumers {

    @Autowired
    public KafkaProperties kafkaProperties;

	@Autowired
	RegraService service;
    
    @KafkaListener(topics = "#{kafkaProperties.getPrefix()}ottimizza.exportar.regras.oud", 
            	   groupId = "#{kafkaProperties.getPrefix()}tareffa-queue-events-group")
    public void integraArquivo(@Payload String message) throws Exception {
    	service.exportarRegrasCrm(message);
    }
}

