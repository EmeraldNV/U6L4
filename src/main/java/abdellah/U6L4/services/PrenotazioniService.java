package abdellah.U6L4.services;

import abdellah.U6L4.entities.Dipendente;
import abdellah.U6L4.entities.Prenotazione;
import abdellah.U6L4.exceptions.BadRequestException;
import abdellah.U6L4.payload.DipendentePayload;
import abdellah.U6L4.payload.PrenotazionePayload;
import abdellah.U6L4.repositories.PrenotazioniRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PrenotazioniService {

    private final PrenotazioniRepository prenotazioniRepository;


    public PrenotazioniService(PrenotazioniRepository prenotazioniRepository) {
        this.prenotazioniRepository = prenotazioniRepository;
    }


    public Prenotazione save(PrenotazionePayload body) {

        Prenotazione nuovaPrenotazione = new Prenotazione(body.getDataRichiesta(), body.getNote(), body.getViaggio(),body.getDipendente());
        Prenotazione prenotazioneSalvata = this.prenotazioniRepository.save(nuovaPrenotazione);

        log.info("La prenotazione  è stata salvata correttamente!");

        return prenotazioneSalvata;
    }


}
