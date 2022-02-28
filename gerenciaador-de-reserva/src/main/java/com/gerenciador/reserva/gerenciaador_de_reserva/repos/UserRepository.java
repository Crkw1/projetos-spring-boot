package com.gerenciador.reserva.gerenciaador_de_reserva.repos;

import com.gerenciador.reserva.gerenciaador_de_reserva.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
