package abdellah.U6L4.services;

import abdellah.U6L4.entities.Dipendente;
import abdellah.U6L4.entities.Prenotazione;
import abdellah.U6L4.entities.Viaggio;
import abdellah.U6L4.exceptions.BadRequestException;
import abdellah.U6L4.exceptions.NotFoundException;
import abdellah.U6L4.payload.DipendentePayload;
import abdellah.U6L4.payload.PrenotazionePayload;
import abdellah.U6L4.repositories.DipendentiRepository;
import abdellah.U6L4.repositories.PrenotazioniRepository;
import abdellah.U6L4.repositories.ViaggiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class PrenotazioniService {

    private final PrenotazioniRepository prenotazioniRepository;
    private final DipendentiRepository dipendentiRepository;
    private final ViaggiRepository viaggiRepository;

    public PrenotazioniService(PrenotazioniRepository prenotazioniRepository, DipendentiRepository dipendentiRepository, ViaggiRepository viaggiRepository) {
        this.prenotazioniRepository = prenotazioniRepository;
        this.dipendentiRepository = dipendentiRepository;
        this.viaggiRepository = viaggiRepository;
    }


    public Prenotazione save(PrenotazionePayload body) {

        Dipendente foundDipendenteId =  dipendentiRepository.findById(body.getDipendente().getId()).orElseThrow(() -> new RuntimeException("Dipendente non trovato"));;
        Viaggio foundViaggioId =  viaggiRepository.findById(body.getViaggio().getId()).orElseThrow(() -> new RuntimeException("Dipendente non trovato"));;

        Prenotazione nuovaPrenotazione = new Prenotazione(body.getDataRichiesta(), body.getNote(), foundViaggioId ,foundDipendenteId );
        Prenotazione prenotazioneSalvata = this.prenotazioniRepository.save(nuovaPrenotazione);
        log.info("La prenotazione  è stata salvata correttamente!");
        return prenotazioneSalvata;
    }

    public Page<Prenotazione> getAllPrenotazioni(Integer page, Integer size, String sortBy){
        if (size > 100 || size < 0) size = 2;
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.prenotazioniRepository.findAll(pageable);

    }

}
