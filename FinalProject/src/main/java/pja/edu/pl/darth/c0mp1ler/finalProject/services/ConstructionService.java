package pja.edu.pl.darth.c0mp1ler.finalProject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.Construction;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.Kingdom;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.RegionImpl;
import pja.edu.pl.darth.c0mp1ler.finalProject.repositories.ConstructionRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Service to fetch data from construction repository
 */
@Service
@RequiredArgsConstructor
public class ConstructionService {

    private final ConstructionRepository constructionRepository;

    /**
     * if no kingdom or region are provided, return list of constructions
     * if region is not null, return list of constructions in the region
     * if only kindgom is not null, return list of constructions in the kingdom
     * @param kingdom kingdom to perform query on
     * @param region region to perform query on
     * @return list of constructions
     */
    public List<Construction> getAllConstructions(Kingdom kingdom, RegionImpl region){
        if(region == null && kingdom == null) {
            Iterable<Construction> constructions = constructionRepository.findAll();
            List<Construction> res = new ArrayList<>();
            constructions.forEach(res::add);
            return res;
        }
        if (region != null){
            return constructionRepository.findByRegion(region);
        }
        return constructionRepository.findByKingdom(kingdom);
    }
}
