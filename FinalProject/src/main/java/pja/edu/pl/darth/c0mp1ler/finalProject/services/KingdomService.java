package pja.edu.pl.darth.c0mp1ler.finalProject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.Construction;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.Kingdom;
import pja.edu.pl.darth.c0mp1ler.finalProject.repositories.KingdomRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Service to fetch data from kingdom repository
 */
@Service
@RequiredArgsConstructor
public class KingdomService {

    private final KingdomRepository kingdomRepository;

    /**
     *
     * @return list of all kingdoms
     */
    public List<Kingdom> getAllKingdoms(){
        Iterable<Kingdom> kingdoms = kingdomRepository.findAll();
        List<Kingdom> res = new ArrayList<>();
        kingdoms.forEach(res :: add);
        return res;
    }

}
