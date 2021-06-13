package pja.edu.pl.darth.c0mp1ler.mp5.repository;

import org.springframework.data.repository.CrudRepository;
import pja.edu.pl.darth.c0mp1ler.mp5.model.Construction;
import pja.edu.pl.darth.c0mp1ler.mp5.model.ConstructionType;

import java.time.LocalDate;
import java.util.List;

public interface ConstructionRepository extends CrudRepository<Construction,Long> {

    public List<Construction> findByName(String name);

    public List<Construction> findByNameAndConstructionType(String name, ConstructionType constructionType);

    public List<Construction> findByConstructionDateAfter(LocalDate localDate);

}
