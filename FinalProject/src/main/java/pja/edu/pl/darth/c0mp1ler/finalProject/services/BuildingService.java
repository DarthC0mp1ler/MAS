package pja.edu.pl.darth.c0mp1ler.finalProject.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.Building;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.Construction;
import pja.edu.pl.darth.c0mp1ler.finalProject.repositories.BuildingRepository;

import java.util.List;

/**
 * Service to fetch data from building repository
 */
@Service
@RequiredArgsConstructor
public class BuildingService {

    private final BuildingRepository buildingRepository;

    /**
     * gets list of buildings in construction
     * @param construction construction to be quering on
     * @return list of buildings in provided construction
     */
    public List<Building> getBuildingsInConstruction(Construction construction){
        return buildingRepository.findByConstruction(construction);
    }

}
