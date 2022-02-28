package com.gerenciador.reserva.gerenciaador_de_reserva.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Reserva {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate reservaDate;

    @DateTimeFormat(pattern = "HH:mm")
    @Column
    private LocalTime startTime;

    @DateTimeFormat(pattern = "HH:mm")
    @Column
    private LocalTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AmenityTipo amenityTipo;

    @PrePersist
    public void prePersist() {
        dateCreated = OffsetDateTime.now();
        lastUpdated = dateCreated;
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdated = OffsetDateTime.now();
    }


    public Reserva(LocalDate reservaDate, LocalTime startTime,
                       LocalTime endTime, User user, AmenityTipo amenityTipo) {
        this.reservaDate = reservaDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.user = user;
        this.amenityTipo = amenityTipo;
    }
}
