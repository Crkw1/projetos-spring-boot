package com.gerenciador_de_reserva.repos;

import com.gerenciador_de_reserva.model.AmenityTipo;
import com.gerenciador_de_reserva.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findReservaByAmenityTipo(AmenityTipo amenityTipo);

    List<Reserva> findReservasByReservaDateAndStartTimeBeforeAndEndTimeAfterOrStartTimeBetween(
            LocalDate reservaDate, LocalTime startTime, LocalTime endTime,
            LocalTime betweenStart, LocalTime betweenEnd);
}
