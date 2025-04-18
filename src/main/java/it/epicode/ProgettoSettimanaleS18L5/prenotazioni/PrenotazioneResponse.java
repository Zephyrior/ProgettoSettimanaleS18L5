package it.epicode.ProgettoSettimanaleS18L5.prenotazioni;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrenotazioneResponse {

    private Long id;
    private LocalDate dataPrenotazione;
    private String preferenze;
    private Long dipendente_id;
    private Long viaggio_id;
}
