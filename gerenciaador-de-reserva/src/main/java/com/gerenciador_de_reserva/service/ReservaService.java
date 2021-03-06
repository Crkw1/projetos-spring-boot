package com.gerenciador_de_reserva.service;

import com.gerenciador_de_reserva.exception.CapacidadeFullException;
import com.gerenciador_de_reserva.model.Reserva;
import com.gerenciador_de_reserva.model.Capacidade;
import com.gerenciador_de_reserva.repos.CapacidadeRepository;
import com.gerenciador_de_reserva.repos.ReservaRepository;
import com.gerenciador_de_reserva.repos.UserRepository;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final CapacidadeRepository capacidadeRepository;

    public ReservaService(final ReservaRepository reservaRepository,
                          final CapacidadeRepository capacidadeRepository) {
        this.reservaRepository = reservaRepository;
        this.capacidadeRepository = capacidadeRepository;
    }

    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    public Reserva get(final Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final Reserva reserva) {
        int capacidade = capacidadeRepository.findByAmenityTipo(reserva.getAmenityTipo()).getCapacidade();
        int overlappingReservas = reservaRepository
                .findReservasByReservaDateAndStartTimeBeforeAndEndTimeAfterOrStartTimeBetween(
                        reserva.getReservaDate(),
                        reserva.getStartTime(), reserva.getEndTime(),
                        reserva.getStartTime(), reserva.getEndTime()).size();

        if (overlappingReservas >= capacidade) {
            throw new CapacidadeFullException("A capacidade desta comodidade está cheia no momento desejado");
        }

        return reservaRepository.save(reserva).getId();
    }

    public void update(final Long id, final Reserva reserva) {
        final Reserva existingReserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        reservaRepository.save(reserva);
    }

    public void delete(final Long id) {
        reservaRepository.deleteById(id);
    }
}
