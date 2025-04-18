package it.epicode.ProgettoSettimanaleS18L5.prenotazioni;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @PatchMapping(path = "/dipendente/prenotazione/{id}")
    public PrenotazioneResponse saveReservation(Long dipendente_id, LocalDate dataPrenotazione, Long viaggio_id){
        return prenotazioneService.saveReservation(dipendente_id, dataPrenotazione, viaggio_id);
    }
}
