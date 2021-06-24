package pja.edu.pl.darth.c0mp1ler.finalProject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.Kingdom;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.Ruler;
import pja.edu.pl.darth.c0mp1ler.finalProject.repositories.RulerRepository;

import java.util.List;

/**
 * Service to fetch data from ruler repository
 */
@Service
@RequiredArgsConstructor
public class RulerService {
    private final RulerRepository rulerRepository;

    /**
     *
     * @param kingdom kingdom to make query on
     * @return list of rulers associated with the provided kingdom
     */
    public List<Ruler> getRulerByKingdom(Kingdom kingdom){
        return rulerRepository.findByKingdom(kingdom);
    }
}
