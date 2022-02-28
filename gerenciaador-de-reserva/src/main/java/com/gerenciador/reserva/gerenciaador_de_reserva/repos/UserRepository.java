package com.gerenciador.reserva.gerenciaador_de_reserva.repos;

import com.gerenciador.reserva.gerenciaador_de_reserva.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    org.springframework.security.core.userdetails.User findUserByUsername(String username);
}
