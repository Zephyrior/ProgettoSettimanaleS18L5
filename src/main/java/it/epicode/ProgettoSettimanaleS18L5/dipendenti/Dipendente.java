package it.epicode.ProgettoSettimanaleS18L5.dipendenti;

import it.epicode.ProgettoSettimanaleS18L5.prenotazioni.Prenotazione;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Dipendenti")

public class Dipendente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String username;

    @Column(nullable = false, length = 50)
    private String nome;

    @Column(nullable = false, length = 50)
    private String cognome;

    @Column(nullable = false, length = 30)
    private String email;

    @Column
    private String fotoProfilo = "https://ui-avatars.com/api/fotoprofilodefault";

    @OneToMany(mappedBy = "dipendente")
    private List<Prenotazione> prenotazioni = new ArrayList<>();


}