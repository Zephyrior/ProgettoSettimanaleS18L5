package it.epicode.ProgettoSettimanaleS18L5.viaggi;


import it.epicode.ProgettoSettimanaleS18L5.stato.Stato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViaggioResponse {

    private Long id;
    private String destinazione;
    private LocalDate dataPartenza;
    private Stato stato;
}
