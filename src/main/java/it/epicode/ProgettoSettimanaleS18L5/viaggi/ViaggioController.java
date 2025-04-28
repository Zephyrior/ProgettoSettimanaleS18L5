package it.epicode.ProgettoSettimanaleS18L5.viaggi;

import it.epicode.ProgettoSettimanaleS18L5.common.CommonResponse;
import it.epicode.ProgettoSettimanaleS18L5.stato.Stato;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {

    @Autowired
    private ViaggioService viaggioService;


    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public List<ViaggioResponse> findAll(){
        return viaggioService.findAll();
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ViaggioResponse findById(@PathVariable Long id){
        return viaggioService.findById(id);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse saveViaggio(@RequestBody @Valid ViaggioRequest request){
        return viaggioService.saveViaggio(request);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping(path = "/stato/{id}")
    public void updateStatoViaggio(@PathVariable Long id, @RequestParam Stato stato){
        viaggioService.updateStatoViaggio(id, stato);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        viaggioService.delete(id);
    }
}
