package abdellah.U6L4.services;

import abdellah.U6L4.entities.Prenotazione;
import abdellah.U6L4.entities.Viaggio;
import abdellah.U6L4.exceptions.BadRequestException;
import abdellah.U6L4.payload.ViaggioPayload;
import abdellah.U6L4.repositories.ViaggiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ViaggiService {

    private final ViaggiRepository viaggiRepository;

    public ViaggiService(ViaggiRepository viaggiRepository) {
        this.viaggiRepository = viaggiRepository;
    }

    public Viaggio save(ViaggioPayload body) {
        if (this.viaggiRepository.existsByData(body.getData()))
            throw new BadRequestException("Esiste gia' un viaggio per questa data! " + body.getData() + "!");

        Viaggio nuovoViaggio = new Viaggio(body.getDestinazione(),body.getData(),body.getStatoViaggio());
        Viaggio viaggioSalvato = this.viaggiRepository.save(nuovoViaggio);

        log.info("Il viaggio per " + viaggioSalvato.getDestinazione() + " è stato salvato correttamente!");

        return viaggioSalvato;
    }
}
