package it.epicode.ProgettoSettimanaleS18L5.viaggi;

import it.epicode.ProgettoSettimanaleS18L5.stato.Stato;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViaggioRequest {

    @NotBlank(message = "Destinazione non può essere vuota")
    private String destinazione;

    @NotNull(message = "Data partenza non può essere nulla")
    private LocalDate dataPartenza;

    @NotNull(message = "Stato non può essere nulla")
    private Stato stato;

}
