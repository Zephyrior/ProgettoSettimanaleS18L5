package it.epicode.ProgettoSettimanaleS18L5.dipendenti;


import it.epicode.ProgettoSettimanaleS18L5.common.CommonResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/dipendente")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    @GetMapping
    public List<Dipendente> findAll(){
        return dipendenteService.findAll();
    }

    @GetMapping("/simple")
    public List<DipendenteResponse> findAllDipendenteSimple(){
        return dipendenteService.findAllDipendenteSimple();
    }

    @GetMapping("/{id}")
    public DipendenteResponse findById(Long id){
        return dipendenteService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse saveDipendente(@RequestBody @Valid DipendenteRequest request){
        return dipendenteService.saveDipendente(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDipendente(Long id){
        dipendenteService.deleteDipendente(id);
    }

    @PatchMapping(path = "/foto-profilo/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadFotoProfilo(@PathVariable Long id, @RequestPart MultipartFile fotoProfilo) {
        dipendenteService.uploadFotoProfilo(id, fotoProfilo);
    }
}
