package pja.edu.pl.darth.c0mp1ler.mp5.repository;

import org.springframework.data.repository.CrudRepository;
import pja.edu.pl.darth.c0mp1ler.mp5.model.Person;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person,Long> {

    public List<Person> findByNameAndFathersName(String name, String fathersName);

}
