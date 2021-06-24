package pja.edu.pl.darth.c0mp1ler.finalProject.repositories;

import org.springframework.data.repository.CrudRepository;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.Kingdom;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.Region;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.RegionImpl;

import java.util.List;

/**
 * Region repository
 */
public interface RegionRepository extends CrudRepository<RegionImpl,Long> {

    /**
     *
     * @param kingdom kingdom the region may belong to
     * @return list of regions in provided kingdom
     */
    public List<RegionImpl> findByKingdom(Kingdom kingdom);

}
