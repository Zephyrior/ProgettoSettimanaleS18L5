package it.epicode.ProgettoSettimanaleS18L5.viaggi;

import it.epicode.ProgettoSettimanaleS18L5.common.CommonResponse;
import it.epicode.ProgettoSettimanaleS18L5.stato.Stato;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class ViaggioService {

    @Autowired
    private ViaggioRepository viaggioRepository;


    //recupera tutti i viaggi
    public List<ViaggioResponse> findAll(){
        return viaggioRepository.findAll()
                .stream()
                .map(v -> new ViaggioResponse(v.getId(),
                        v.getDestinazione(),
                        v.getDataPartenza(),
                        v.getStato()))
                .toList();
    }

    //recupera un viaggio tramite l'id
    public ViaggioResponse findById(Long id){
        Viaggio viaggio = viaggioRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Viaggio non trovato"));

        ViaggioResponse response = new ViaggioResponse();
        BeanUtils.copyProperties(viaggio, response);

        return response;
    }

    //salva un nuovo viaggio
    public CommonResponse saveViaggio(ViaggioRequest request){
        Viaggio viaggio = new Viaggio();
        BeanUtils.copyProperties(request, viaggio);
        viaggioRepository.save(viaggio);


        return new CommonResponse(viaggio.getId());
    }

    //aggiorna lo stato di un viaggio
    public void updateStatoViaggio(Long id, Stato stato){
        Viaggio viaggio = viaggioRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Viaggio non trovato"));

        viaggio.setStato(stato);
        viaggioRepository.save(viaggio);
    }

    //elimina un viaggio tramite l'id
    public void delete(Long id) {
        Viaggio viaggio = viaggioRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Viaggio non trovato"));

        viaggioRepository.deleteById(id);
    }
}
