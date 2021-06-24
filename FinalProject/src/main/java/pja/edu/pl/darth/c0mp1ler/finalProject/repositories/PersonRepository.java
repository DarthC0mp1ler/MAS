package pja.edu.pl.darth.c0mp1ler.finalProject.repositories;

import org.springframework.data.repository.CrudRepository;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.Person;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.enums.Race;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.enums.Sex;

import java.util.List;

/**
 * Person repository
 */
public interface PersonRepository extends CrudRepository<Person,Long> {

    /**
     *
     * @param sex sex type
     * @see Sex
     * @return list of people of the provided sex
     */
    public List<Person> findBySex(Sex sex);

    /**
     *
     * @param race race type
     * @see Race
     * @return list of people of provided race
     */
    public List<Person> findByRace(Race race);

}
