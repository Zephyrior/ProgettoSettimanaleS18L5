package it.epicode.ProgettoSettimanaleS18L5.viaggi;

import com.github.javafaker.Faker;
import it.epicode.ProgettoSettimanaleS18L5.stato.Stato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;

@Configuration
@Order(1)
public class ViaggioRunner implements CommandLineRunner {

    @Autowired
    private Faker faker;

    @Autowired
    private ViaggioRepository viaggioRepository;


    @Override
    public void run(String... args) throws Exception {

        if (viaggioRepository.count() == 0) {
            for (int i = 0; i < 5; i++) {
                Viaggio viaggio = new Viaggio();
                viaggio.setDataPartenza(LocalDate.now().plusDays((long) (Math.random() * 7)));
                viaggio.setDestinazione(faker.address().country());
                viaggio.setStato(Stato.IN_PROGRAMMA);

                viaggioRepository.save(viaggio);
            }

            for (int i = 0; i < 5; i++) {
                Viaggio viaggioPassato = new Viaggio();
                viaggioPassato.setDataPartenza(LocalDate.now().minusDays(((long) Math.random() * 7)));
                viaggioPassato.setDestinazione(faker.address().country());
                viaggioPassato.setStato(Stato.COMPLETATO);

                viaggioRepository.save(viaggioPassato);
            }
        }
    }
}
