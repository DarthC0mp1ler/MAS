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
import java.util.List;
@DataJpaTest
public class MethodsForUseCaseTest {

    @Autowired
    private RulerRepository rulerRepository;
    @Autowired
    private KingdomRepository kingdomRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private ConstructionRepository constructionRepository;
    @Autowired
    private GovernorRepository governorRepository;
    @Autowired
    private LandlordRepository landlordRepository;
    @PersistenceContext
    EntityManager manager;
    Ruler r;
    Ruler r2;
    Governor g1;
    Governor g2;
    Kingdom kingdom1;
    Kingdom kingdom2;
    RegionImpl region1;
    RegionImpl region2;
    Construction construction1;
    Construction construction2;
    Construction construction3;
    Construction construction4;
    Landlord l1;

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
                .name("name1")
                .familyName("family name")
                .fathersName("fathers name")
                .wealth(4)
                .sex(Sex.Male)
                .race(Race.Human)
                .startRuling(LocalDate.now())
                .birthDate(LocalDate.of(2010,3,4))
                .build();
        /////////////////GOVERNORS//////////////
        g1 = Governor.builder()
                .name("governor 1")
                .familyName("family name")
                .fathersName("fathers name")
                .wealth(4)
                .sex(Sex.Male)
                .race(Race.Human)
                .birthDate(LocalDate.of(2000,3,4))
                .build();
        g2 = Governor.builder()
                .name("governor 2")
                .familyName("family name")
                .fathersName("fathers name")
                .wealth(4)
                .sex(Sex.Male)
                .race(Race.Human)
                .birthDate(LocalDate.of(2000,3,4))
                .build();
        ////////////////Landlord///////////////
        l1 = Landlord.builder()
                .name("governor 2")
                .familyName("family name")
                .fathersName("fathers name")
                .wealth(4)
                .sex(Sex.Male)
                .race(Race.Human)
                .birthDate(LocalDate.of(2000,3,4))
                .previousGenerations(1)
                .build();
        /////////////////KINGDOMS//////////////
        kingdom1 = Kingdom.builder()
                .name("kingdom1")
                .color(Arrays.asList(255,255,255))
                .coordinates(new Coordinates(123,23, LatDir.N, LongDir.E))
                .build();
        kingdom2 = Kingdom.builder()
                .name("kingdom2")
                .color(Arrays.asList(255,255,255))
                .coordinates(new Coordinates(123,23, LatDir.N, LongDir.E))
                .build();
        /////////////////REGIONS///////////////////
        region1 = RegionImpl.builder()
                .name("region 1")
                .color(Arrays.asList(0,-100,500))
                .climate("mountain")
                .coordinates(new Coordinates(123,23, LatDir.N, LongDir.E))
                .build();
        region2 = RegionImpl.builder()
                .name("region 2")
                .color(Arrays.asList(0,-100,500))
                .climate("mountain")
                .coordinates(new Coordinates(123,23, LatDir.N, LongDir.E))
                .build();
        /////////////////CONSTRUCTIONS///////////////
        construction1 = Construction.builder()
                .name("construction 1")
                .color(Arrays.asList(0,-100,500))
                .coordinates(new Coordinates(123,23, LatDir.N, LongDir.E))
                .constructionDate(LocalDate.now())
                .constructionStatus(ConstructionStatus.Standard)
                .constructionType(ConstructionType.City)
                .build();
        construction2 = Construction.builder()
                .name("construction 2")
                .color(Arrays.asList(0,-100,500))
                .coordinates(new Coordinates(123,23, LatDir.N, LongDir.E))
                .constructionDate(LocalDate.now())
                .constructionStatus(ConstructionStatus.Standard)
                .constructionType(ConstructionType.City)
                .build();
        construction3 = Construction.builder()
                .name("construction 3")
                .color(Arrays.asList(0,-100,500))
                .coordinates(new Coordinates(123,23, LatDir.N, LongDir.E))
                .constructionDate(LocalDate.now())
                .constructionStatus(ConstructionStatus.Standard)
                .constructionType(ConstructionType.City)
                .build();
        construction4 = Construction.builder()
                .name("construction 4")
                .color(Arrays.asList(0,-100,500))
                .coordinates(new Coordinates(123,23, LatDir.N, LongDir.E))
                .constructionDate(LocalDate.now())
                .constructionStatus(ConstructionStatus.Standard)
                .constructionType(ConstructionType.City)
                .build();

        l1.setGovernor(g1);
        g1.getLandlords().add(l1);

        construction1.setLandlord(l1);
        l1.getConstructions().add(construction1);
        construction2.setLandlord(l1);
        l1.getConstructions().add(construction2);
        construction3.setLandlord(l1);
        l1.getConstructions().add(construction3);
        construction4.setLandlord(l1);
        l1.getConstructions().add(construction4);

        kingdom1.setRuler(r);
        r.setKingdom(kingdom1);
        kingdom2.setRuler(r2);
        r2.setKingdom(kingdom2);

        kingdom1.getRegions().add(region1);
        region1.setKingdom(kingdom1);


        kingdom2.getRegions().add(region2);
        region2.setKingdom(kingdom2);

        region1.setGovernor(g1);
        g1.setRegion(region1);

        region2.setGovernor(g2);
        g2.setRegion(region2);

        region1.getConstructions().add(construction1);
        construction1.setRegion(region1);
        region1.getConstructions().add(construction2);
        construction2.setRegion(region1);
        region1.getConstructions().add(construction3);
        construction3.setRegion(region1);
        region2.getConstructions().add(construction4);
        construction4.setRegion(region2);

        rulerRepository.save(r);
        rulerRepository.save(r2);
        kingdomRepository.save(kingdom1);
        kingdomRepository.save(kingdom2);
        governorRepository.save(g1);
        governorRepository.save(g2);
        regionRepository.save(region1);
        regionRepository.save(region2);
        landlordRepository.save(l1);
        constructionRepository.save(construction1);
        constructionRepository.save(construction2);
        constructionRepository.save(construction3);
        constructionRepository.save(construction4);
        manager.flush();
    }

    @Test
    public void findByKingdomTest(){
        List<Construction> byKingdom1 = constructionRepository.findByKingdom(kingdom1);
        System.out.println("\nFIRST KINGDOM");
        for (Construction c: byKingdom1) {
            System.out.println("\t\t" + c + "  " + c.getRegion().getName() + "  " + c.getRegion().getKingdom().getName());
        }
        List<Construction> byKingdom2 = constructionRepository.findByKingdom(kingdom2);
        System.out.println("\nSECOND KINGDOM");
        for (Construction c: byKingdom2) {
            System.out.println("\t\t" + c + "  " + c.getRegion().getName() + "  " + c.getRegion().getKingdom().getName());
        }
    }
    @Test
    public void findByRegionTest(){
        List<Construction> byRegion1 = constructionRepository.findByRegion(region1);
        System.out.println("\nFIRST Region");
        for (Construction c: byRegion1) {
            System.out.println("\t\t" + c + "  " + c.getRegion().getName() + "  " + c.getRegion().getKingdom().getName());
        }
        List<Construction> byRegion2 = constructionRepository.findByRegion(region2);
        System.out.println("\nSECOND Region");
        for (Construction c: byRegion2) {
            System.out.println("\t\t" + c + "  " + c.getRegion().getName() + "  " + c.getRegion().getKingdom().getName());
        }
    }

    @Test
    public void findGovernorByRegionTest(){
        System.out.println("\nFIRST Region");
        List<Governor> byRegion1 = governorRepository.findByRegion(region1);
        for (Governor g: byRegion1) {
            System.out.println("\t\t" + g);
        }
        System.out.println("\nSECOND Region");
        List<Governor> byRegion2 = governorRepository.findByRegion(region1);
        for (Governor g: byRegion2) {
            System.out.println("\t\t" + g);
        }
    }

    @Test
    public void findRulerByKingdomTest(){
        System.out.println("\nFIRST KINGDOM");
        List<Ruler> byKingdom1 = rulerRepository.findByKingdom(kingdom1);
        for (Ruler r: byKingdom1) {
            System.out.println("\t\t" + r);
        }
        System.out.println("\nSECOND KINGDOM");
        List<Ruler> byKingdom2 = rulerRepository.findByKingdom(kingdom1);
        for (Ruler r: byKingdom2) {
            System.out.println("\t\t" + r);
        }
    }

}
