package it.epicode.ProgettoSettimanaleS18L5.dipendenti;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DipendenteRequest {

    @NotBlank(message = "Username non può essere vuoto")
    private String username;

    @NotBlank(message = "Nome non può essere vuoto")
    private String nome;

    @NotBlank(message = "Cognome non può essere vuoto")
    private String cognome;

    @Email(message = "Email non valida")
    @NotBlank(message = "Email non può essere vuota")
    private String email;

    private String fotoProfilo;
}
