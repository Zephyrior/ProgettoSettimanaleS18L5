package it.epicode.ProgettoSettimanaleS18L5.viaggi;

import it.epicode.ProgettoSettimanaleS18L5.prenotazioni.Prenotazione;
import it.epicode.ProgettoSettimanaleS18L5.stato.Stato;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Viaggi")

public class Viaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 50)
    private String destinazione;

    @Column(nullable = false)
    private LocalDate dataPartenza;

    @Column
    @Enumerated(EnumType.STRING)
    private Stato stato;

    @OneToMany(mappedBy = "viaggio")
    private List<Prenotazione> prenotazioni;

}