package pja.edu.pl.darth.c0mp1ler.finalProject.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.Kingdom;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.Ruler;

import java.util.List;

/**
 * Ruler repository
 */
public interface RulerRepository extends CrudRepository<Ruler,Long> {

    /**
     *
     * @param kingdom kingdom the ruler may rule
     * @return list of rulers associated with selected kingdom
     */
    @Query("SELECT r FROM Ruler r join Kingdom k on r.kingdom.id = k.id where k = :kg")
    public List<Ruler> findByKingdom(@Param("kg") Kingdom kingdom);

}
