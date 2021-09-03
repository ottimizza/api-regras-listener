package br.com.ottimizza.regraslistener.config.kafka;

import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    private final Logger LOG = LoggerFactory.getLogger(KafkaConsumerConfig.class);

    @Autowired
    KafkaProperties properties;

    @Bean
    public Map<String, Object> consumerConfigs() {
        System.out.println("");
        System.out.println(properties.getUrl());
        Map<String, Object> config = (Map) properties.buildDefaults();
        return config;

        // Map<String, Object> props = new HashMap<>();
        // props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "bootstrapServers");
        // props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
        // StringDeserializer.class);
        // return props;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<String, String>(consumerConfigs());
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

}
