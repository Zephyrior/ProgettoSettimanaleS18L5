package it.epicode.ProgettoSettimanaleS18L5.prenotazioni;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping(path = "/dipendente")
    @ResponseStatus(HttpStatus.CREATED)
    public PrenotazioneResponse saveReservation(@RequestParam Long dipendente_id, @Valid @RequestBody PrenotazioneRequest request, @RequestParam Long viaggio_id){
        return prenotazioneService.saveReservation(dipendente_id, request, viaggio_id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public PrenotazioneResponse findById(@PathVariable Long id){
        return prenotazioneService.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<PrenotazioneResponse> findAll(){
        return prenotazioneService.findAll();
    }

    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        prenotazioneService.delete(id);
    }
}
