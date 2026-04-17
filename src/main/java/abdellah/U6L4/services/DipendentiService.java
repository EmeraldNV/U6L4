package abdellah.U6L4.services;


import abdellah.U6L4.entities.Dipendente;
import abdellah.U6L4.exceptions.BadRequestException;
import abdellah.U6L4.exceptions.NotFoundException;
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

        Dipendente nuovoDipendente = new Dipendente(body.getUsername(),body.getNome(), body.getCognome(), body.getEmail());
        Dipendente dipendenteSalvato = this.dipendentiRepository.save(nuovoDipendente);

        log.info("Il dipendente con id " + dipendenteSalvato.getId() + " è stato salvato correttamente!");

        return dipendenteSalvato;
    }

    public Page<Dipendente> getAllDipendenti(Integer page, Integer size, String sortBy){
        if (size > 100 || size < 0) size = 10;
        if (page < 0) page = 0;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.dipendentiRepository.findAll(pageable);

    }

    public Dipendente findById(Long dipendenteId) {
        return this.dipendentiRepository.findById(dipendenteId).orElseThrow(() -> new NotFoundException(dipendenteId));
    }
}
