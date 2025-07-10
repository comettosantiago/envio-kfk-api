package com.kfk.envio_kfk_api.api.envioproducer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kfk.envio_kfk_api.api.envioproducer.entities.Compra;
import com.kfk.envio_kfk_api.api.envioproducer.entities.envio.Envio;
import com.kfk.envio_kfk_api.api.envioproducer.entities.envio.EnvioRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CompraConsumerEnvioProducerServiceImpl implements CompraConsumerEnvioProducerService {

    private final EnvioRepository envioRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public CompraConsumerEnvioProducerServiceImpl(EnvioRepository envioRepository, KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.envioRepository = envioRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "compra.realizada", groupId = "group-envios")
    public void consume(String json) throws JsonProcessingException {
        Compra compra = objectMapper.readValue(json, Compra.class);
        Envio envio = new Envio(compra.getId(), "TRACK12345", false);
        envio = envioRepository.save(envio);

        try {
            String envioJson = objectMapper.writeValueAsString(envio);
            kafkaTemplate.send("envio.generado", envioJson);
            System.out.println("Envío publicado en el topic: envio.generado");
        } catch (Exception e) {
            System.err.println("Error al serializar el envío: " + e.getMessage());
        }
    }

}
