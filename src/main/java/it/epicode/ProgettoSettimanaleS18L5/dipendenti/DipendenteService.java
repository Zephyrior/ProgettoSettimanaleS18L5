package it.epicode.ProgettoSettimanaleS18L5.dipendenti;

import it.epicode.ProgettoSettimanaleS18L5.cloudinary.CloudinaryService;
import it.epicode.ProgettoSettimanaleS18L5.common.CommonResponse;
import it.epicode.ProgettoSettimanaleS18L5.prenotazioni.PrenotazioneRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Service
@Validated
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    //verifica se il dipendente può effettuare la prenotazione alla data interessata
    public boolean canReserve(Dipendente dipendente, PrenotazioneRequest request){
        return dipendente.getPrenotazioni()
                .stream()
                .noneMatch(prenotazione -> prenotazione.getDataPrenotazione()
                .equals(request.getDataPrenotazione()));
    };

    //recupera tutti i dipendenti
    public List<Dipendente> findAll(){
        return dipendenteRepository.findAll();
    }

    public DipendenteResponse findById(Long id){
        Dipendente dipendente = dipendenteRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));

        DipendenteResponse response = new DipendenteResponse();
        response.setNomeCognome(dipendente.getNome() + " " + dipendente.getCognome());
        BeanUtils.copyProperties(dipendente, response);

        return response;
    }

    //salva un nuovo dipendente
    public CommonResponse saveDipendente(DipendenteRequest request){
        Dipendente dipendente = new Dipendente();

        BeanUtils.copyProperties(request, dipendente);

        dipendenteRepository.save(dipendente);

        return new CommonResponse(dipendente.getId());
    }

    //recupera tutti i dipendenti in modo semplice
    public List<DipendenteResponse> findAllDipendenteSimple(){
        List<Dipendente> dipendenti = dipendenteRepository.findAll();
        return dipendenti.stream()
                .map(d -> new DipendenteResponse(d.getId(),
                        d.getUsername(),
                        d.getNome ()+ " " + d.getCognome(),
                        d.getEmail(),
                        d.getFotoProfilo()))
                .toList();

    }

    //elimina un dipendente tramite l'id
    public void deleteDipendente(Long id){
        Dipendente dipendente = dipendenteRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));

        dipendenteRepository.delete(dipendente);
    }

    //carica la foto profilo del dipendente
    public void uploadFotoProfilo(Long id, MultipartFile fotoProfilo) {
        Dipendente dipendente = dipendenteRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dipendente non trovato"));

        String url = cloudinaryService.upload(fotoProfilo);

        dipendente.setFotoProfilo(url);
        dipendenteRepository.save(dipendente);
    }




}
