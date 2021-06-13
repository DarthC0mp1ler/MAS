package pja.edu.pl.darth.c0mp1ler.mp5.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import pja.edu.pl.darth.c0mp1ler.mp5.model.Landlord;

import java.util.List;
import java.util.Optional;

public interface LandlordRepository extends CrudRepository<Landlord,Long> {

    public List<Landlord> findByWealthGreaterThan(int wealth);
    public List<Landlord> findByWealthLessThan(int wealth);

    @Query("FROM Landlord AS l LEFT JOIN FETCH l.constructions WHERE l.id = :id")
    public Optional<Landlord> findById(@Param("id") long id);

}
