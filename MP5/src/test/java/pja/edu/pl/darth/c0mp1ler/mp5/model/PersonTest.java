package pja.edu.pl.darth.c0mp1ler.mp5.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pja.edu.pl.darth.c0mp1ler.mp5.repository.GovernorRepository;
import pja.edu.pl.darth.c0mp1ler.mp5.repository.LandlordRepository;
import pja.edu.pl.darth.c0mp1ler.mp5.repository.PersonRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class PersonTest {

    @Autowired
    private GovernorRepository governorRepository;
    @Autowired
    private LandlordRepository landlordRepository;
    @Autowired
    private PersonRepository personRepository;

    @BeforeEach
    void setUp(){
        Governor g = Governor.builder()
                .name("Alcine")
                .fathersName("Fatherdaughter")
                .governingRegionName("Dimitrescu")
                .build();
        governorRepository.save(g);
        g = Governor.builder()
                .name("Vivec")
                .fathersName("Fatherson")
                .governingRegionName("Vvanderfel")
                .build();
        governorRepository.save(g);
        Landlord l = Landlord.builder()
                .name("John")
                .fathersName("Eigarson")
                .wealth(1789)
                .build();
        landlordRepository.save(l);
    }

    @Test
    void repositoryCreationTest(){
        assertNotNull(personRepository);
        assertNotNull(governorRepository);
        assertNotNull(landlordRepository);
    }

    @Test
    void fetchRepoTest(){
        Iterable<Person> all = personRepository.findAll();
        for (Person p : all) {
            System.out.println(p);
        }
        Iterable<Governor> all1 = governorRepository.findAll();
        for (Person p : all1) {
            System.out.println(p);
        }
        Iterable<Landlord> all2 = landlordRepository.findAll();
        for (Person p : all2) {
            System.out.println(p);
        }
    }

    @Test
    void findByNameAndFathersNameTest(){
        List<Person> byNameAndFathersName = personRepository.findByNameAndFathersName("Vivec", "Fatherson");
        System.out.println(byNameAndFathersName);
        assertEquals(1,byNameAndFathersName.size());
    }

    @Test
    void findByWealthBeforeAndAfter(){
        List<Landlord> byWealthAfter = landlordRepository.findByWealthGreaterThan(1700);
        System.out.println(byWealthAfter);
        assertEquals(1,byWealthAfter.size());
        List<Landlord> byWealthBefore = landlordRepository.findByWealthLessThan(1900);
        System.out.println(byWealthBefore);
        assertEquals(1,byWealthBefore.size());
    }

    @Test
    void findByGoverningRegion(){
        List<Governor> dimitrescu = governorRepository.findByGoverningRegionName("Dimitrescu");
        System.out.println(dimitrescu);
        assertEquals(1,dimitrescu.size());
    }

}