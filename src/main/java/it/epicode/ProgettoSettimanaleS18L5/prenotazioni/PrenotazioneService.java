package it.epicode.ProgettoSettimanaleS18L5.prenotazioni;

import it.epicode.ProgettoSettimanaleS18L5.dipendenti.Dipendente;
import it.epicode.ProgettoSettimanaleS18L5.dipendenti.DipendenteRepository;
import it.epicode.ProgettoSettimanaleS18L5.dipendenti.DipendenteService;
import it.epicode.ProgettoSettimanaleS18L5.viaggi.Viaggio;
import it.epicode.ProgettoSettimanaleS18L5.viaggi.ViaggioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private ViaggioRepository viaggioRepository;

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private DipendenteService dipendenteService;

    public PrenotazioneResponse saveReservation(Long dipendente_id, LocalDate dataPrenotazione, Long viaggio_id){

        Dipendente dipendente = dipendenteRepository.findById(dipendente_id)
                .orElseThrow(()-> new EntityNotFoundException("Dipendente non trovato"));

        Viaggio viaggio = viaggioRepository.findById(viaggio_id)
                .orElseThrow(()-> new EntityNotFoundException("Viaggio non trovato"));



        if(!dipendenteService.canReserve(dipendente, dataPrenotazione)){
            throw new IllegalArgumentException("Dipendente non puo' effettuare la prenotazione alla data interessata");
        }


        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setDataPrenotazione(dataPrenotazione);
        prenotazione.setDipendente(dipendente);
        prenotazione.setViaggio(viaggio);
        prenotazioneRepository.save(prenotazione);

        PrenotazioneResponse response = new PrenotazioneResponse();
        response.setDataPrenotazione(dataPrenotazione);
        response.setDipendente_id(dipendente_id);
        response.setViaggio_id(viaggio_id);


        return response;
    }
}
