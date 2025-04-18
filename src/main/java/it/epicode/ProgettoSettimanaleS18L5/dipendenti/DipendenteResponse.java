package it.epicode.ProgettoSettimanaleS18L5.dipendenti;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DipendenteResponse {

    private Long id;
    private String username;
    private String nomeCognome;
    private String email;
    private String fotoProfilo;

}
