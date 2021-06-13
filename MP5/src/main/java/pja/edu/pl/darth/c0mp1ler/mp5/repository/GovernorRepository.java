package pja.edu.pl.darth.c0mp1ler.mp5.repository;

import org.springframework.data.repository.CrudRepository;
import pja.edu.pl.darth.c0mp1ler.mp5.model.Governor;

import java.util.List;

public interface GovernorRepository extends CrudRepository<Governor,Long> {

    public List<Governor> findByGoverningRegionName(String governingRegion);

}
