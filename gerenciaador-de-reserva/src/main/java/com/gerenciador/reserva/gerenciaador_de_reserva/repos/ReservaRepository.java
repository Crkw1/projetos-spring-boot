package com.gerenciador.reserva.gerenciaador_de_reserva.repos;

import com.gerenciador.reserva.gerenciaador_de_reserva.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;


public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    int findReservasByReservaDateAndStartTimeBeforeAndEndTimeAfterOrStartTimeBetween(
            LocalDate reservaDate, LocalTime startTime, LocalTime endTime,
            LocalTime betweenStart, LocalTime betweenEnd);
}
