package pja.edu.pl.darth.c0mp1ler.finalProject.models.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pja.edu.pl.darth.c0mp1ler.finalProject.exceptions.ContentViolationException;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.enums.Race;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.enums.Sex;
import pja.edu.pl.darth.c0mp1ler.finalProject.repositories.FractionRepository;
import pja.edu.pl.darth.c0mp1ler.finalProject.repositories.GovernorRepository;
import pja.edu.pl.darth.c0mp1ler.finalProject.repositories.PersonRepository;
import pja.edu.pl.darth.c0mp1ler.finalProject.repositories.RulerRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PersonAndFractionTest {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private FractionRepository fractionRepository;
    @Autowired
    private RulerRepository rulerRepository;
    @Autowired
    private GovernorRepository governorRepository;
    @PersistenceContext
    private EntityManager manager;

    Ruler r1;
    Ruler r2;
    Governor g1;
    Governor g2;

    Fraction fraction;

    @BeforeEach
    public void init(){
        r1 = Ruler.builder()
                .name("Ruler1")
                .familyName("family name")
                .fathersName("fathers name")
                .wealth(4)
                .sex(Sex.Male)
                .race(Race.Human)
                .startRuling(LocalDate.now())
                .birthDate(LocalDate.of(2010,3,4))
                .build();
        r2 = Ruler.builder()
                .name("Ruler2")
                .familyName("family name")
                .fathersName("fathers name")
                .wealth(4)
                .sex(Sex.Male)
                .race(Race.Human)
                .startRuling(LocalDate.now())
                .birthDate(LocalDate.of(2010,3,4))
                .build();
        g1 = Governor.builder()
                .name("Governor1")
                .familyName("family name")
                .fathersName("fathers name")
                .wealth(4)
                .sex(Sex.Male)
                .race(Race.Human)
                .birthDate(LocalDate.of(2010,3,4))
                .build();
        g2 = Governor.builder()
                .name("Governor2")
                .familyName("family name")
                .fathersName("fathers name")
                .wealth(4)
                .sex(Sex.Male)
                .race(Race.Human)
                .birthDate(LocalDate.of(2010,3,4))
                .build();
        fraction = Fraction.builder()
                .support(10)
                .against(r1)
                .build();

        fraction.addRebel(r2);
        r2.setFraction(fraction);

        fraction.addRebel(g1);
        g1.setFraction(fraction);

//        fraction.addRebel(g2);
//        g2.setFraction(fraction);

        System.out.println();
        fraction.getRebels().forEach(System.out::println);
        System.out.println();

        fraction.setLeader(r2);

        rulerRepository.save(r1);
        rulerRepository.save(r2);
        governorRepository.save(g1);
        governorRepository.save(g2);
        fractionRepository.save(fraction);
        manager.flush();
    }

    @Test
    public void fractionTest(){
        Iterable<Fraction> fractionRepositoryAll = fractionRepository.findAll();
        System.out.println();
        for (Fraction f: fractionRepositoryAll) {
            f.getRebels().forEach(System.out::println);
            System.out.println();
            System.out.println(f.getAgainst());
            System.out.println(f.getLeader());
        }
        assertThrows(ContentViolationException.class, () -> {
           fraction.setLeader(g2);
        });
//        fraction.addRebel(g2);
//        fraction.setLeader(g2);

        assertThrows(ContentViolationException.class, ()->{
            fraction.addRebel(r1);
        });


    }


}
