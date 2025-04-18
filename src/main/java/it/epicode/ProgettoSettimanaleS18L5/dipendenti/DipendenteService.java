package it.epicode.ProgettoSettimanaleS18L5.dipendenti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    public boolean canReserve(Dipendente dipendente, LocalDate dataPrenotazione){
        return dipendente.getPrenotazioni()
                .stream()
                .noneMatch(prenotazione -> prenotazione.getDataPrenotazione()
                .equals(dataPrenotazione));
    };
}
