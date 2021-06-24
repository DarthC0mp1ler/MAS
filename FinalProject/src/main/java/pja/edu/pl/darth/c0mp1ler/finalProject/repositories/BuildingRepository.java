package pja.edu.pl.darth.c0mp1ler.finalProject.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.Building;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.Construction;

import java.util.List;

/**
 * Repository for building
 * @see Building
 */
public interface BuildingRepository extends CrudRepository<Building,Long> {

    /**
     *
     * @param construction Construction to which a building belongs
     * @return a list of buildings in provided construction
     */
    @Query("SELECT b FROM Building b join Construction c on b.construction.id = c.id where c = :constr")
    public List<Building> findByConstruction(@Param("constr") Construction construction);

}
