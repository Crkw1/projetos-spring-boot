package com.gerenciador.reserva.gerenciaador_de_reserva.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("com.gerenciador.reserva.gerenciaador_de_reserva.domain")
@EnableJpaRepositories("com.gerenciador.reserva.gerenciaador_de_reserva.repos")
@EnableTransactionManagement
public class DomainConfig {
}
