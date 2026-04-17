package abdellah.U6L4.services;

import abdellah.U6L4.entities.Dipendente;
import abdellah.U6L4.entities.Prenotazione;
import abdellah.U6L4.entities.Viaggio;
import abdellah.U6L4.exceptions.BadRequestException;
import abdellah.U6L4.exceptions.NotFoundException;
import abdellah.U6L4.payload.ViaggioPayload;
import abdellah.U6L4.repositories.ViaggiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static abdellah.U6L4.enums.StatoViaggio.COMPLETATO;
import static abdellah.U6L4.enums.StatoViaggio.IN_PROGRAMMA;

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


        Viaggio nuovoViaggio = new Viaggio(body.getDestinazione(),body.getData());
        Viaggio viaggioSalvato = this.viaggiRepository.save(nuovoViaggio);

        log.info("Il viaggio per " + viaggioSalvato.getDestinazione() + " è stato salvato correttamente!");

        return viaggioSalvato;
    }

    public Page<Viaggio> getAllViaggi(Integer page, Integer size, String sortBy){
        if (size > 100 || size < 0) size = 2;
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.viaggiRepository.findAll(pageable);

    }

    public Viaggio findById(Long viaggioId) {
        return this.viaggiRepository.findById(viaggioId).orElseThrow(() -> new NotFoundException(viaggioId));
    }
}
