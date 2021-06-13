package pja.edu.pl.darth.c0mp1ler.mp5.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pja.edu.pl.darth.c0mp1ler.mp5.repository.ConstructionRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ConstructionTest {

    Construction construction1;
    Construction construction2;

    @Autowired
    ConstructionRepository constructionRepository;

    @PersistenceContext
    EntityManager manager;

    @BeforeEach
    void setUp() {
        construction1 = Construction.builder()
                .name("Kaer Morhen")
                .constructionDate(LocalDate.of(1478,5,16))
                .constructionType(ConstructionType.Fortress)
                .build();
        construction2 = Construction.builder()
                .name("Whiterun")
                .constructionDate(LocalDate.of(1765,3,27))
                .constructionType(ConstructionType.City)
                .build();
        constructionRepository.save(construction1);
        constructionRepository.save(construction2);
        manager.flush();
    }

    @Test
    void repositoryCreationTest(){
        assertNotNull(constructionRepository);
    }

    @Test
    void fetchDataTest() {
        Iterable<Construction> all = constructionRepository.findAll();
        for (Construction c: all) {
            assertNotNull(c);
            System.out.println(c);
        }
    }

    @Test
    void findByNameTest() {
        List<Construction> whiterun = constructionRepository.findByName("Whiterun");
        System.out.println(whiterun);
        assertEquals(1,whiterun.size());
    }

    @Test
    void findByConstructionTypeTest(){
        List<Construction> kaer_morhen = constructionRepository.findByNameAndConstructionType("Kaer Morhen", ConstructionType.Fortress);
        System.out.println(kaer_morhen);
        assertEquals(1,kaer_morhen.size());
    }

    void findByConstructionDateAfter(){
        List<Construction> byConstructionDateAfter = constructionRepository.findByConstructionDateAfter(LocalDate.of(1600, 1, 1));
        System.out.println(byConstructionDateAfter);
        assertEquals(1,byConstructionDateAfter.size());
    }

}