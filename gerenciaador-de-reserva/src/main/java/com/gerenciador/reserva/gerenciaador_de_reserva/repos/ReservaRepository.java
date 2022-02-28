package com.gerenciador.reserva.gerenciaador_de_reserva.repos;

import com.gerenciador.reserva.gerenciaador_de_reserva.domain.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
