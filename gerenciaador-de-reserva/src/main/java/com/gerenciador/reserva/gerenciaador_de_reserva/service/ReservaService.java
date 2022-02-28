package com.gerenciador.reserva.gerenciaador_de_reserva.service;

import com.gerenciador.reserva.gerenciaador_de_reserva.domain.Reserva;
import com.gerenciador.reserva.gerenciaador_de_reserva.domain.User;
import com.gerenciador.reserva.gerenciaador_de_reserva.model.ReservaDTO;
import com.gerenciador.reserva.gerenciaador_de_reserva.repos.ReservaRepository;
import com.gerenciador.reserva.gerenciaador_de_reserva.repos.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final UserRepository userRepository;

    public ReservaService(final ReservaRepository reservaRepository,
            final UserRepository userRepository) {
        this.reservaRepository = reservaRepository;
        this.userRepository = userRepository;
    }

    public List<ReservaDTO> findAll() {
        return reservaRepository.findAll()
                .stream()
                .map(reserva -> mapToDTO(reserva, new ReservaDTO()))
                .collect(Collectors.toList());
    }

    public ReservaDTO get(final Long id) {
        return reservaRepository.findById(id)
                .map(reserva -> mapToDTO(reserva, new ReservaDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final ReservaDTO reservaDTO) {
        final Reserva reserva = new Reserva();
        mapToEntity(reservaDTO, reserva);
        return reservaRepository.save(reserva).getId();
    }

    public void update(final Long id, final ReservaDTO reservaDTO) {
        final Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(reservaDTO, reserva);
        reservaRepository.save(reserva);
    }

    public void delete(final Long id) {
        reservaRepository.deleteById(id);
    }

    private ReservaDTO mapToDTO(final Reserva reserva, final ReservaDTO reservaDTO) {
        reservaDTO.setId(reserva.getId());
        reservaDTO.setReservaDate(reserva.getReservaDate());
        reservaDTO.setStartTime(reserva.getStartTime());
        reservaDTO.setEndTime(reserva.getEndTime());
        reservaDTO.setUser(reserva.getUser() == null ? null : reserva.getUser().getId());
        return reservaDTO;
    }

    private Reserva mapToEntity(final ReservaDTO reservaDTO, final Reserva reserva) {
        reserva.setReservaDate(reservaDTO.getReservaDate());
        reserva.setStartTime(reservaDTO.getStartTime());
        reserva.setEndTime(reservaDTO.getEndTime());
        if (reservaDTO.getUser() != null && (reserva.getUser() == null || !reserva.getUser().getId().equals(reservaDTO.getUser()))) {
            final User user = userRepository.findById(reservaDTO.getUser())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
            reserva.setUser(user);
        }
        return reserva;
    }

}
