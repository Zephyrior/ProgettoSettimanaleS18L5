package it.epicode.ProgettoSettimanaleS18L5.prenotazioni;

import it.epicode.ProgettoSettimanaleS18L5.dipendenti.Dipendente;
import it.epicode.ProgettoSettimanaleS18L5.dipendenti.DipendenteRepository;
import it.epicode.ProgettoSettimanaleS18L5.dipendenti.DipendenteService;
import it.epicode.ProgettoSettimanaleS18L5.viaggi.Viaggio;
import it.epicode.ProgettoSettimanaleS18L5.viaggi.ViaggioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;

@Service
@Validated
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private ViaggioRepository viaggioRepository;

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private DipendenteService dipendenteService;


    // salva una nuova prenotazione in base alla disponibilità del dipendente
    public PrenotazioneResponse saveReservation(Long dipendente_id, PrenotazioneRequest request, Long viaggio_id){

        Dipendente dipendente = dipendenteRepository.findById(dipendente_id)
                .orElseThrow(()-> new EntityNotFoundException("Dipendente non trovato"));

        Viaggio viaggio = viaggioRepository.findById(viaggio_id)
                .orElseThrow(()-> new EntityNotFoundException("Viaggio non trovato"));



        if(!dipendenteService.canReserve(dipendente, request)){
            throw new IllegalArgumentException("Dipendente non puo' effettuare la prenotazione alla data interessata");
        }


        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setDataPrenotazione(request.getDataPrenotazione());
        prenotazione.setPreferenze(request.getPreferenze());
        prenotazione.setDipendente(dipendente);
        prenotazione.setViaggio(viaggio);
        prenotazioneRepository.save(prenotazione);

        PrenotazioneResponse response = new PrenotazioneResponse();
        response.setDataPrenotazione(request.getDataPrenotazione());
        response.setPreferenze(request.getPreferenze());
        response.setDipendente_id(dipendente_id);
        response.setViaggio_id(viaggio_id);


        return response;
    }


    // trova una prenotazione tramite l'id
    public PrenotazioneResponse findById(Long id) {
        Prenotazione prenotazione = prenotazioneRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prenotazione non trovata"));

        PrenotazioneResponse response = new PrenotazioneResponse();
        BeanUtils.copyProperties(prenotazione, response);

        return response;
    }

    // trova tutte le prenotazioni
    public List<PrenotazioneResponse> findAll() {
        List<Prenotazione> prenotazioni = prenotazioneRepository.findAll();
        return prenotazioni.stream()
                .map(p ->
                    new PrenotazioneResponse( p.getId(),
                            p.getDataPrenotazione(),
                            p.getPreferenze(),
                            p.getDipendente().getId(),
                            p.getViaggio().getId()))
                .toList();
    }

    public void delete(Long id) {
        Prenotazione prenotazione = prenotazioneRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Prenotazione non trovata"));

        prenotazioneRepository.deleteById(id);
    }
}
