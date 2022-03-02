package com.gerenciador_de_reserva;

import com.gerenciador_de_reserva.model.AmenityTipo;
import com.gerenciador_de_reserva.model.Capacidade;
import com.gerenciador_de_reserva.model.User;
import com.gerenciador_de_reserva.repos.CapacidadeRepository;
import com.gerenciador_de_reserva.repos.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class GerenciadorDeReservaApplication {

    private final Map<AmenityTipo, Integer> initialCapacities =
            new HashMap<>() {
                {
                    put(AmenityTipo.POOL, 20);
                    put(AmenityTipo.SAUNA, 4);
                    put(AmenityTipo.SALA, 1);
                    put(AmenityTipo.CONDOMINIO, 12);
                    put(AmenityTipo.ACADEMIA, 7);
                }
            };

    public static void main(String[] args) {
        SpringApplication.run(GerenciadorDeReservaApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(
            UserRepository userRepository,
            CapacidadeRepository capacidadeRepository) {
        return (args) -> {
            userRepository.save(
                new User("teste123", "teste",
                        bCryptPasswordEncoder().encode("12345")));

            for (AmenityTipo amenityTipo : initialCapacities.keySet()) {
                capacidadeRepository.save(new Capacidade(amenityTipo, initialCapacities.get(amenityTipo)));
            }
        };
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }
}
