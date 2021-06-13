package pja.edu.pl.darth.c0mp1ler.mp5.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pja.edu.pl.darth.c0mp1ler.mp5.repository.ConstructionRepository;
import pja.edu.pl.darth.c0mp1ler.mp5.repository.LandlordRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BasicAssociationTest {

    @Autowired
    private LandlordRepository landlordRepository;

    @Autowired
    private ConstructionRepository constructionRepository;

    private Landlord landlord;

    @BeforeEach
    void setUp(){
         landlord = Landlord.builder()
                .name("name")
                .fathersName("fathersName")
                .wealth(13453)
                .build();
        Construction construction = Construction.builder()
                .name("constr")
                .constructionType(ConstructionType.City)
                .constructionDate(LocalDate.now())
                .manager(landlord)
                .build();
        constructionRepository.save(construction);
        landlord.getConstructions().add(construction);
        construction = Construction.builder()
                .name("constr2")
                .constructionType(ConstructionType.City)
                .constructionDate(LocalDate.now())
                .manager(landlord)
                .build();
        landlord.getConstructions().add(construction);
        constructionRepository.save(construction);
        landlordRepository.save(landlord);
    }

    @Test
    void repositoryCreationTest(){
        assertNotNull(landlordRepository);
        assertNotNull(constructionRepository);
    }

    @Test
    void assocTest(){
        Optional<Landlord> byId = landlordRepository.findById(landlord.getId());
        assertTrue(byId.isPresent());
        System.out.println(byId.get().getConstructions());
        assertEquals(2,byId.get().getConstructions().size());
    }

}
