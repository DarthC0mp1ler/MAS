package pja.edu.pl.darth.c0mp1ler.finalProject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.Kingdom;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.RegionImpl;
import pja.edu.pl.darth.c0mp1ler.finalProject.repositories.RegionRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Service to fetch data from region repository
 */
@Service
@RequiredArgsConstructor
public class RegionService {

    public final RegionRepository regionRepository;

    /**
     * if no kingdom is provided, return a list of all regions
     * otherwise, return list of regions in the kingdom
     * @param kingdom kingdom to be making query on
     * @return list of regionImpl
     */
    public List<RegionImpl> getRegionsByKingdom(Kingdom kingdom){
        if(kingdom == null) {
            Iterable<RegionImpl> regions = regionRepository.findAll();
            List<RegionImpl> res = new ArrayList<>();
            regions.forEach(res :: add);
            return res;
        }
        return regionRepository.findByKingdom(kingdom);
    }
}



