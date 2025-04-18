package it.epicode.ProgettoSettimanaleS18L5.dipendenti;


import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {
}