package it.epicode.ProgettoSettimanaleS18L5.viaggi;

import it.epicode.ProgettoSettimanaleS18L5.common.CommonResponse;
import it.epicode.ProgettoSettimanaleS18L5.stato.Stato;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {

    @Autowired
    private ViaggioService viaggioService;


    @GetMapping
    public List<ViaggioResponse> findAll(){
        return viaggioService.findAll();
    }

    @GetMapping("/{id}")
    public ViaggioResponse findById(@PathVariable Long id){
        return viaggioService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse saveViaggio(@RequestBody @Valid ViaggioRequest request){
        return viaggioService.saveViaggio(request);
    }

    @PatchMapping(path = "/stato/{id}")
    public void updateStatoViaggio(@PathVariable Long id, @RequestParam Stato stato){
        viaggioService.updateStatoViaggio(id, stato);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        viaggioService.delete(id);
    }
}
