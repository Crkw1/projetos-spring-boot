package com.gerenciador.reserva.gerenciaador_de_reserva;

import com.gerenciador.reserva.gerenciaador_de_reserva.model.AmenityTipo;
import com.gerenciador.reserva.gerenciaador_de_reserva.model.Capacidade;
import com.gerenciador.reserva.gerenciaador_de_reserva.model.User;
import com.gerenciador.reserva.gerenciaador_de_reserva.repos.CapacidadeRepository;
import com.gerenciador.reserva.gerenciaador_de_reserva.repos.ReservaRepository;
import com.gerenciador.reserva.gerenciaador_de_reserva.repos.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class GerenciaadorDeReservaApplication {

    private Map<AmenityTipo, Integer> initialCapacites =
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
        SpringApplication.run(GerenciaadorDeReservaApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository,
                                      ReservaRepository reservaRepository,
                                      CapacidadeRepository capacidadeRepository) {
        return (args) -> {
            User user =
                    userRepository.save(
                            new User("William Almeida",
                                    "Aloha",
                                    bCryptPasswordEncoder().encode("12345")));
            for (AmenityTipo amenityTipo : initialCapacites.keySet()) {
                capacidadeRepository.save(new Capacidade(amenityTipo, initialCapacites.get(amenityTipo)));
            }
        };
    }
            /*
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Reserva reserva =
                    Reserva.builder()
                            .reservationDate(localDate)
                            .startTime(LocalTime.of(12, 00))
                            .endTime(LocalTime.of(13, 00))
                            .user(user)
                            .amenityTipo(AmenityTipo.POOL)
                            .build();

            reservaRepository.save(reserva);
        };
    }*/

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
