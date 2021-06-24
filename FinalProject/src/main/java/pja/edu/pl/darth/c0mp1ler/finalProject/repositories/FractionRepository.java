package pja.edu.pl.darth.c0mp1ler.finalProject.repositories;

import org.springframework.data.repository.CrudRepository;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.Fraction;

/**
 * Fraction repository
 */
public interface FractionRepository extends CrudRepository<Fraction,Long> {
}
