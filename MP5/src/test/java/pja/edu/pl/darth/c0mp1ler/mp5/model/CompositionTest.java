package pja.edu.pl.darth.c0mp1ler.mp5.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pja.edu.pl.darth.c0mp1ler.mp5.repository.BuildingRepository;
import pja.edu.pl.darth.c0mp1ler.mp5.repository.ConstructionRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CompositionTest {
    @Autowired
     ConstructionRepository constructionRepository;
    @Autowired
    BuildingRepository buildingRepository;

    Construction c;
    @BeforeEach
    void setUp() {
        c = Construction.builder()
                .name("Kaer Morhen")
                .constructionDate(LocalDate.of(1478,5,16))
                .constructionType(ConstructionType.Fortress)
                .build();
        Building b1 = Building.builder()
                .name("build1")
                .type(BuildingType.Church)
                .construction(c)
                .build();
        c.getBuildings().add(b1);
        Building b2 = Building.builder()
                .name("build2")
                .type(BuildingType.Farm)
                .construction(c)
                .build();
        c.getBuildings().add(b2);
        constructionRepository.save(c);
        buildingRepository.save(b1);
        buildingRepository.save(b2);


    }

    @Test
    void repositoryCreationTest(){
        assertNotNull(constructionRepository);
        assertNotNull(buildingRepository);
    }

    @Test
    void removeTest(){
        System.out.println(constructionRepository.findAll());
        System.out.println(buildingRepository.findAll());
        constructionRepository.delete(c);
        Iterable<Construction> all = constructionRepository.findAll();
        System.out.println(all);
        Iterable<Building> all1 = buildingRepository.findAll();
        System.out.println(all1);
    }
}