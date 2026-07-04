package com.duoc.plataformaeducativa.servicio.impl;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.duoc.plataformaeducativa.config.RabbitMQConfig;
import com.duoc.plataformaeducativa.servicio.RabbitProducer;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RabbitProducerImpl implements RabbitProducer {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void enviarResumen(String resumen) {

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.RESUMEN_EXCHANGE,
                RabbitMQConfig.RESUMEN_ROUTING_KEY,
                resumen
        );
    }
}
