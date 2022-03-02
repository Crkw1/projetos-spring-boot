package com.gerenciador_de_reserva.repos;

import com.gerenciador_de_reserva.model.AmenityTipo;
import com.gerenciador_de_reserva.model.Capacidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CapacidadeRepository extends JpaRepository <Capacidade, Long> {
    Capacidade findByAmenityTipo(AmenityTipo amenityTipo);
}
