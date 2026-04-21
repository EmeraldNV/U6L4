package abdellah.U6L4.services;


import abdellah.U6L4.entities.Dipendente;
import abdellah.U6L4.exceptions.BadRequestException;
import abdellah.U6L4.exceptions.NotFoundException;
import abdellah.U6L4.exceptions.NotFoundExceptionString;
import abdellah.U6L4.payload.DipendentePayload;
import abdellah.U6L4.repositories.DipendentiRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class DipendentiService {
    private final DipendentiRepository dipendentiRepository;

    public DipendentiService(DipendentiRepository dipendentiRepository) {
        this.dipendentiRepository = dipendentiRepository;
    }

    public Dipendente save(DipendentePayload body) {
        if (this.dipendentiRepository.existsByEmail(body.getEmail()))
            throw new BadRequestException("L'indirizzo email " + body.getEmail() + " è già in uso!");

        Dipendente nuovoDipendente = new Dipendente(body.getUsername(),body.getNome(), body.getCognome(), body.getEmail(), body.getPassword());
        Dipendente dipendenteSalvato = this.dipendentiRepository.save(nuovoDipendente);

        log.info("Il dipendente con id " + dipendenteSalvato.getId() + " è stato salvato correttamente!");

        return dipendenteSalvato;
    }

    public Page<Dipendente> getAllDipendenti(Integer page, Integer size, String sortBy){
        if (size > 100 || size < 0) size = 2;
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.dipendentiRepository.findAll(pageable);
    }

    public Dipendente findById(Long dipendenteId) {
        return this.dipendentiRepository.findById(dipendenteId).orElseThrow(() -> new NotFoundException(dipendenteId));
    }

    public Dipendente findByIdAndUpdate(Long dipendenteId, DipendentePayload body) {
        Dipendente utenteDaModificare = this.findById(dipendenteId);
        if(!utenteDaModificare.getEmail().equals(body.getEmail()))
            throw new BadRequestException("L'indirizzo email " + body.getEmail() + " è già in uso!");

utenteDaModificare.setNome(body.getNome());
utenteDaModificare.setCognome(body.getCognome());
utenteDaModificare.setNome(body.getEmail());
utenteDaModificare.setNome(body.getPassword());

    Dipendente dipendenteDaAggiornare = this.dipendentiRepository.save(utenteDaModificare);
    return dipendenteDaAggiornare;
    }

    public void findByIdAndDelete(Long dipendenteId) {
        Dipendente utenteDaCancellare = this.findById(dipendenteId);
        this.dipendentiRepository.delete((utenteDaCancellare));
    }

    public Dipendente findByEmail(String email) {
        return this.dipendentiRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundExceptionString("L'utente con email " + email + " non è stato trovato!"));
    }
}
