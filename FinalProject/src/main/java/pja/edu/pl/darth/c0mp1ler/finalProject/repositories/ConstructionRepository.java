package pja.edu.pl.darth.c0mp1ler.finalProject.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.Construction;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.Kingdom;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.Region;

import java.util.List;

/**
 * Construction Repository
 */
public interface ConstructionRepository extends CrudRepository<Construction,Long> {

    /**
     *
     * @param region Region constructions may belong to
     * @return list of constructions in the provided region
     */
    public List<Construction> findByRegion(Region region);

    /**
     *
     * @param kingdom Kingdom construction may belong to
     * @return list of Construction in a kingdom provided
     */
    @Query("SELECT c FROM Construction c join Region r on c.region.id = r.id join Kingdom k on r.kingdom.id = k.id where k = :k")
    public List<Construction> findByKingdom(@Param("k") Kingdom kingdom);
}
