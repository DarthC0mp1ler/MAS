package pja.edu.pl.darth.c0mp1ler.finalProject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.Governor;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.Region;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.RegionImpl;
import pja.edu.pl.darth.c0mp1ler.finalProject.repositories.GovernorRepository;

import java.util.List;

/**
 * Service to fetch data from governor repository
 */
@Service
@RequiredArgsConstructor
public class GovernorService {

    private final GovernorRepository governorRepository;

    /**
     *
     * @param region region to make query on
     * @return list of governors associated with provided region
     */
    public List<Governor> getGovernorByRegion(RegionImpl region){
        return governorRepository.findByRegion(region);
    }

}
