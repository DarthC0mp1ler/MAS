package pja.edu.pl.darth.c0mp1ler.mp5.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import pja.edu.pl.darth.c0mp1ler.mp5.repository.GoverningContractRepository;
import pja.edu.pl.darth.c0mp1ler.mp5.repository.GovernorRepository;
import pja.edu.pl.darth.c0mp1ler.mp5.repository.LandlordRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AssociationWithAttributeTest {

    @Autowired
    private LandlordRepository landlordRepository;
    @Autowired
    private GovernorRepository governorRepository;
    @Autowired
    private GoverningContractRepository governingContractRepository;
    Landlord l;
    Governor g;

    @BeforeEach
    void SetUp() {
        l = Landlord.builder()
                .name("John")
                .fathersName("Eigarson")
                .wealth(1789)
                .build();
        g = Governor.builder()
                .name("Alcine")
                .fathersName("Fatherdaughter")
                .governingRegionName("Dimitrescu")
                .build();
        GoverningContract c = GoverningContract.builder()
                .tax(23)
                .signDate(LocalDate.now())
                .landlord(l)
                .governor(g)
                .build();
        l.setContract(c);
        g.getContracts().add(c);
        landlordRepository.save(l);
        governorRepository.save(g);
        governingContractRepository.save(c);
    }

    @Test
    void repositoryCreationTest() {
        assertNotNull(landlordRepository);
        assertNotNull(governingContractRepository);
        assertNotNull(governorRepository);
    }

    @Test
    void asscocRemoveTest() {
        Iterable<GoverningContract> all = governingContractRepository.findAll();
        System.out.println(all);
        assertNotNull(all);
        governorRepository.delete(g);
        landlordRepository.delete(l);
        all = governingContractRepository.findAll();
        System.out.println(all);
        assertNotNull(all);
    }

    @Test
    void notUniqueTest() {
        assertThrows(DataIntegrityViolationException.class, ()-> {
            GoverningContract contract = GoverningContract.builder()
                    .tax(23)
                    .signDate(LocalDate.now())
                    .landlord(l)
                    .governor(g)
                    .build();
            governingContractRepository.save(contract);
            Iterable<GoverningContract> all = governingContractRepository.findAll();
            System.out.println(all);
            assertNotNull(all);
        });
    }

}
