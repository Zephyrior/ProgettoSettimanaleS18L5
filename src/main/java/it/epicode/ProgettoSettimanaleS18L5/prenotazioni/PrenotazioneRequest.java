package it.epicode.ProgettoSettimanaleS18L5.prenotazioni;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrenotazioneRequest {

    @NotNull(message = "Data prenotazione non può essere nulla")
    @FutureOrPresent(message = "Data prenotazione deve essere nel futuro o oggi")
    private LocalDate dataPrenotazione;

    private String preferenze;
}
