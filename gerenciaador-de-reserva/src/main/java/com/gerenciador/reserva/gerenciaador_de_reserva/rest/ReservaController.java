package com.gerenciador.reserva.gerenciaador_de_reserva.rest;

import com.gerenciador.reserva.gerenciaador_de_reserva.model.ReservaDTO;
import com.gerenciador.reserva.gerenciaador_de_reserva.service.ReservaService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/reservas", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(final ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> getAllReservas() {
        return ResponseEntity.ok(reservaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> getReserva(@PathVariable final Long id) {
        return ResponseEntity.ok(reservaService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createReserva(@RequestBody @Valid final ReservaDTO reservaDTO) {
        return new ResponseEntity<>(reservaService.create(reservaDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateReserva(@PathVariable final Long id,
            @RequestBody @Valid final ReservaDTO reservaDTO) {
        reservaService.update(id, reservaDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserva(@PathVariable final Long id) {
        reservaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
