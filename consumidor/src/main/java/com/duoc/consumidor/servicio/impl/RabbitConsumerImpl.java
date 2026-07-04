package com.duoc.consumidor.servicio.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.duoc.consumidor.config.RabbitMQConfig;
import com.duoc.consumidor.entidad.ResumenInscripcion;
import com.duoc.consumidor.repositorio.ResumenInscripcionRepository;
import com.duoc.consumidor.servicio.RabbitConsumer;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RabbitConsumerImpl implements RabbitConsumer {

    private final ResumenInscripcionRepository repository;

    @Override
    @RabbitListener(queues = RabbitMQConfig.RESUMEN_QUEUE)
    public void recibirResumen(String resumen) {

        ResumenInscripcion entidad = new ResumenInscripcion();

        entidad.setResumen(resumen);

        repository.save(entidad);

        System.out.println("Resumen recibido:");
        System.out.println(resumen);
    }
}
