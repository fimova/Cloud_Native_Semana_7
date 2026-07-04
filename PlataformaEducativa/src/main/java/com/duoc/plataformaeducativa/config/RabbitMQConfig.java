package com.duoc.plataformaeducativa.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;

@Configuration
public class RabbitMQConfig {

	public static final String RESUMEN_QUEUE = "resumen.inscripcion.queue";
    public static final String RESUMEN_EXCHANGE = "resumen.inscripcion.exchange";
    public static final String RESUMEN_ROUTING_KEY = "resumen.inscripcion";

	@Value("${spring.rabbitmq.host}")
	private String host;

	@Value("${spring.rabbitmq.port}")
	private int port;

	@Value("${spring.rabbitmq.username}")
	private String username;

	@Value("${spring.rabbitmq.password}")
	private String password;

	@Bean
	MessageConverter messageConverter() {
		return new SimpleMessageConverter();
	}

	@Bean
	CachingConnectionFactory connectionFactory() {

		CachingConnectionFactory factory = new CachingConnectionFactory();
		factory.setHost(host);
		factory.setPort(port);
		factory.setUsername(username);
		factory.setPassword(password);
		return factory;
	}

	@Bean
	Queue resumenQueue() {

		return new Queue(RESUMEN_QUEUE, true, false, false, null);
	}

	@Bean
	DirectExchange resumenExchange() {

		return new DirectExchange(RESUMEN_EXCHANGE);
	}

	@Bean
	Binding binding(Queue resumenQueue, DirectExchange resumenExchange) {

		return BindingBuilder.bind(resumenQueue).to(resumenExchange).with(RESUMEN_ROUTING_KEY);
	}

	@Bean
	RabbitTemplate rabbitTemplate(
			ConnectionFactory connectionFactory,
			MessageConverter messageConverter) {

		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);

		rabbitTemplate.setMessageConverter(messageConverter);

		return rabbitTemplate;
	}
}
