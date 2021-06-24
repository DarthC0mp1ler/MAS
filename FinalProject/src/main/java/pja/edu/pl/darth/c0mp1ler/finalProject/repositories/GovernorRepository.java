package pja.edu.pl.darth.c0mp1ler.finalProject.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.Governor;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.RegionImpl;

import java.util.List;

/**
 * Governor repository
 */
public interface GovernorRepository extends CrudRepository<Governor,Long> {

    /**
     * @param region RegionImp this governor may govern
     * @return list of governors associated with provided region
     */
    @Query("SELECT g FROM Governor g join Region r on g.region.id = r.id where r = :reg")
    public List<Governor> findByRegion(@Param("reg") RegionImpl region);

}
