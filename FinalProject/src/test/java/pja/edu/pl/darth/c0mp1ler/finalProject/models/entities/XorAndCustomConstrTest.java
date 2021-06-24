package pja.edu.pl.darth.c0mp1ler.finalProject.models.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.enums.*;
import pja.edu.pl.darth.c0mp1ler.finalProject.repositories.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class XorAndCustomConstrTest {

    @Autowired
    private RulerRepository rulerRepository;
    @Autowired
    private GovernorRepository governorRepository;
    @Autowired
    private KingdomRepository kingdomRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private CapitalRepository capitalRepository;
    @Autowired
    private LandlordRepository landlordRepository;
    @PersistenceContext
    EntityManager manager;

    Ruler r;
    Ruler r2;
    Governor g;
    Governor g1;
    Kingdom kingdom;
    RegionImpl region;

    @BeforeEach
    void SetUp() {
        r = Ruler.builder()
                .name("name1")
                .familyName("family name")
                .fathersName("fathers name")
                .wealth(4)
                .sex(Sex.Male)
                .race(Race.Human)
                .startRuling(LocalDate.now())
                .birthDate(LocalDate.of(2010,3,4))
                .build();
        r2 = Ruler.builder()
                .name("name2")
                .familyName("family name")
                .fathersName("fathers name")
                .wealth(4)
                .sex(Sex.Female)
                .race(Race.Elf)
                .magicType(MagicType.Defensive)
                .startRuling(LocalDate.now())
                .birthDate(LocalDate.of(1000,3,4))
                .build();
        g = Governor.builder()
                .name("name1")
                .familyName("family name")
                .fathersName("fathers name")
                .wealth(4)
                .sex(Sex.Male)
                .race(Race.Human)
                .birthDate(LocalDate.of(2000,3,4))
                .build();
        g1 = Governor.builder()
                .name("name2")
                .familyName("family name")
                .fathersName("fathers name")
                .wealth(4)
                .sex(Sex.Male)
                .race(Race.Human)
                .birthDate(LocalDate.of(2000,3,4))
                .build();
        kingdom = Kingdom.builder()
                .name("kingdom")
                .color(Arrays.asList(255,255,255))
                .coordinates(new Coordinates(123,23, LatDir.N, LongDir.E))
                .build();
        region = RegionImpl.builder()
                .name("builder")
                .color(Arrays.asList(0,-100,500))
                .climate("mountain")
                .coordinates(new Coordinates(123,23, LatDir.N, LongDir.E))
                .build();

        Capital capital = Capital.builder()
                .name("builder")
                .color(Arrays.asList(0,-100,500))
                .climate("mountain")
                .coordinates(new Coordinates(123,23, LatDir.N, LongDir.E))
                .constructionDate(LocalDate.now())
                .constructionType(ConstructionType.City)
                .constructionStatus(ConstructionStatus.Standard)
                .build();
        Landlord landlord = Landlord.builder()
                .name("name1")
                .familyName("family name")
                .fathersName("fathers name")
                .wealth(4)
                .sex(Sex.Male)
                .race(Race.Human)
                .birthDate(LocalDate.of(2000,3,4))
                .previousGenerations(1)
                .build();

        landlord.setGovernor(g);
        g.getLandlords().add(landlord);

        capital.setLandlord(landlord);
        landlord.getConstructions().add(capital);

        region.getConstructions().add(capital);
        capital.setRegion(region);

        kingdom.getRegions().add(region);
        region.setKingdom(kingdom);

        r.setLivingPlace(capital);
        capital.setRuler(r);

        kingdom.setRuler(r);
        r.setKingdom(kingdom);

        region.setGovernor(g);
        g.setRegion(region);

        governorRepository.save(g);
        governorRepository.save(g1);
        regionRepository.save(region);
        rulerRepository.save(r);
        kingdomRepository.save(kingdom);
        landlordRepository.save(landlord);
        capitalRepository.save(capital);
        manager.flush();
    }

    @Test
    void repositoryCreationTest() {
        assertNotNull(rulerRepository);
        assertNotNull(governorRepository);
    }

    @Test
    void xorTest(){
        Iterable<Governor> governors = governorRepository.findAll();
        for (Governor g:governors) {
            assertNull(g.getCouncil());
        }

        g.setCouncil(kingdom);
        region.setGovernor(g1);
        g1.setRegion(region);
        governorRepository.save(g);
        governorRepository.save(g1);
        manager.flush();

        manager.refresh(g);
        manager.refresh(region);
        manager.refresh(kingdom);

        governors = governorRepository.findAll();
        for (Governor g:governors) {
            System.out.println(g);
        }
        Iterable<RegionImpl> regions = regionRepository.findAll();
        for (RegionImpl r: regions){
            System.out.println(r);
        }
        Iterable<Kingdom> kingdoms = kingdomRepository.findAll();
        for (Kingdom k: kingdoms){
            assertEquals(1,k.getCouncil().size());
        }

        g.setRegion(region);
        governorRepository.save(g);
        manager.flush();

        manager.refresh(g);
        manager.refresh(region);
        manager.refresh(kingdom);

        governors = governorRepository.findAll();
        for (Governor g:governors) {
            assertNotNull(g.getRegion());
            assertNull(g.getCouncil());
        }
        regions = regionRepository.findAll();
        for (RegionImpl r: regions){
            assertNotNull(r.getGovernor());
        }
        kingdoms = kingdomRepository.findAll();
        for (Kingdom k: kingdoms){
            assertEquals(0,k.getCouncil().size());
        }
    }

    @Test
    public void custom(){
        kingdom.setRegent(r2);
        assertNotNull(kingdom.getRegent());
        kingdom.setRuler(r2);
        kingdom.setRegent(null);
        kingdom.setRegent(r);
        assertNull(kingdom.getRegent());
    }

}
