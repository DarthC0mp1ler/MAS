package pja.edu.pl.darth.c0mp1ler.finalProject;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.entities.*;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.enums.*;
import pja.edu.pl.darth.c0mp1ler.finalProject.repositories.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class with a simple script for the database population if it is empty
 */
@Component
@RequiredArgsConstructor
public class MockDataCreator {

    private final PersonRepository personRepository;
    private final RulerRepository rulerRepository;
    private final GovernorRepository governorRepository;
    private final GoverningContractRepository governingContractRepository;
    private final LandlordRepository landlordRepository;
    private final MapComponentRepository mapComponentRepository;
    private final KingdomRepository kingdomRepository;
    private final RegionRepository regionRepository;
    private final ConstructionRepository constructionRepository;
    private final BuildingRepository buildingRepository;

    private Ruler ruler1;
    private Ruler ruler2;
    private Ruler ruler3;

    private Governor governor1;
    private Governor governor2;
    private Governor governor3;
    private Governor governor4;

    private GoverningContract contract1;
    private GoverningContract contract2;
    private GoverningContract contract3;
    private GoverningContract contract4;

    private Landlord landlord1;
    private Landlord landlord2;
    private Landlord landlord3;
    private Landlord landlord4;
    private Landlord landlord5;
    private Landlord landlord6;

    private Kingdom kingdom1;
    private Kingdom kingdom2;
    private Kingdom kingdom3;

    private RegionImpl region1;
    private RegionImpl region2;
    private RegionImpl region3;
    private RegionImpl region4;

    private List<Construction> constructions = new ArrayList();
    private List<RegionImpl> regions;
    private List<Kingdom> kingdoms;

    private static final String namesPath = "src/main/resources/static/Locations.txt";

    private List<String> names;

    @EventListener
    public void atStart(ContextRefreshedEvent evt){
        Iterable<Person> all = personRepository.findAll();
        for (Person p: all) {
            return;
        }
        createDate();
    }

    private void createDate(){
        names = ReadStrings(namesPath);
        RulerCreate();
        GovernorCreator();
        GoverningContractCreator();
        LandlordCreate();
        KingdomCreate();
        RegionCreate();
        SaveData();
        RandomizeConstruction();

    }

    private List<String> ReadStrings(String path){
        List<String> list = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path))){
            String line = null;
            while((line = bufferedReader.readLine()) != null){
                if(!line.isBlank()){
                    list.add(line);
                }
            }
        }catch (Exception e){
            //
        }
        return list;
    }

    private void RulerCreate(){
        ruler1 = Ruler.builder()
                .name("Ayrenn")
                .familyName("of Somerset")
                .fathersName("fathers name")
                .wealth(5000)
                .sex(Sex.Female)
                .race(Race.Elf)
                .magicType(MagicType.Offensive)
                .startRuling(LocalDate.now())
                .birthDate(LocalDate.of(2010,3,4))
                .build();
        ruler2 = Ruler.builder()
                .name("Emeric")
                .familyName("of Highrock")
                .fathersName("Oskar")
                .wealth(1244)
                .sex(Sex.Male)
                .race(Race.Human)
                .startRuling(LocalDate.now())
                .birthDate(LocalDate.of(2010,3,4))
                .build();
        ruler3 = Ruler.builder()
                .name("Jorunn")
                .familyName("of Skyrim")
                .fathersName("Eigar")
                .wealth(14533)
                .sex(Sex.Male)
                .race(Race.Human)
                .startRuling(LocalDate.now())
                .birthDate(LocalDate.of(2010,3,4))
                .build();
    }
    private void GovernorCreator(){
        governor1 = Governor.builder()
                .name("Elisif")
                .familyName("of Solitude")
                .fathersName("Jorundir")
                .wealth(255)
                .sex(Sex.Female)
                .race(Race.Human)
                .birthDate(LocalDate.of(2010,3,4))
                .build();
        governor1.getManagingSkills().add("time management");
        governor1.getManagingSkills().add("excellent defending practice");

        governor2 = Governor.builder()
                .name("Balgruf")
                .familyName("of Whiterun")
                .fathersName("Stendal")
                .wealth(543)
                .sex(Sex.Male)
                .race(Race.Human)
                .birthDate(LocalDate.of(2010,3,4))
                .build();
        governor2.getManagingSkills().add("good defending practice");
        governor2.getManagingSkills().add("good treasure management");

        governor3 = Governor.builder()
                .name("Ulfric")
                .familyName("Stormcloak")
                .fathersName("Osvald")
                .wealth(546)
                .sex(Sex.Male)
                .race(Race.Human)
                .birthDate(LocalDate.of(2010,3,4))
                .build();
        governor3.getManagingSkills().add("good defending practice");
        governor3.getManagingSkills().add("bad treasure management");
        governor3.getManagingSkills().add("lowering criminal rate");

        governor4 = Governor.builder()
                .name("Elinian")
                .familyName("Sovendien")
                .fathersName("Elianir")
                .wealth(255)
                .sex(Sex.Male)
                .race(Race.Elf)
                .magicType(MagicType.Scholar)
                .birthDate(LocalDate.of(2010,3,4))
                .build();
        governor4.getManagingSkills().add("perfect defending practice");
        governor4.getManagingSkills().add("acceptable treasure management");
        governor4.getManagingSkills().add("citizens morale enhancing");
        governor4.getManagingSkills().add("lowering criminal rate");
    }
    private void GoverningContractCreator(){
        contract1 = GoverningContract.builder()
                .signDate(LocalDate.now())
                .tax(1000)
                .governor(governor1)
                .ruler(ruler1)
                .build();

        contract2 = GoverningContract.builder()
                .signDate(LocalDate.now())
                .tax(1000)
                .governor(governor2)
                .ruler(ruler2)
                .build();

        contract3 = GoverningContract.builder()
                .signDate(LocalDate.now())
                .tax(1000)
                .governor(governor3)
                .ruler(ruler3)
                .build();

        contract4 = GoverningContract.builder()
                .signDate(LocalDate.now())
                .tax(1000)
                .governor(governor4)
                .ruler(ruler1)
                .build();

    }
    private void LandlordCreate(){
        landlord1 = Landlord.builder()
                .name("Varenti")
                .familyName("Avenicci")
                .fathersName("Loreno")
                .wealth(65)
                .sex(Sex.Male)
                .race(Race.Human)
                .birthDate(LocalDate.of(2000,3,4))
                .previousGenerations(5)
                .build();

        landlord2 = Landlord.builder()
                .name("Ogruz")
                .familyName("Bergarie")
                .fathersName("Boguz")
                .wealth(34)
                .sex(Sex.Female)
                .race(Race.Human)
                .birthDate(LocalDate.of(2000,3,4))
                .previousGenerations(5)
                .build();

        landlord3 = Landlord.builder()
                .name("Lucreti")
                .familyName("Avano")
                .fathersName("Lordran")
                .wealth(65)
                .sex(Sex.Male)
                .race(Race.Human)
                .birthDate(LocalDate.of(2000,3,4))
                .previousGenerations(5)
                .build();

        landlord4 = Landlord.builder()
                .name("Isolda")
                .familyName("Jorved")
                .fathersName("Skljald")
                .wealth(555)
                .sex(Sex.Female)
                .race(Race.Human)
                .birthDate(LocalDate.of(2000,3,4))
                .previousGenerations(5)
                .build();

        landlord5 = Landlord.builder()
                .name("Loren")
                .familyName("Alfien")
                .fathersName("Efienir")
                .wealth(899)
                .sex(Sex.Female)
                .race(Race.Elf)
                .magicType(MagicType.Healing)
                .birthDate(LocalDate.of(2000,3,4))
                .previousGenerations(5)
                .build();

        landlord6 = Landlord.builder()
                .name("Loraine")
                .familyName("Avenicci")
                .fathersName("Afian")
                .wealth(65)
                .sex(Sex.Male)
                .race(Race.Elf)
                .magicType(MagicType.Healing)
                .birthDate(LocalDate.of(2000,3,4))
                .previousGenerations(5)
                .build();

        landlord1.setGovernor(governor1);
        governor1.getLandlords().add(landlord1);
        landlord2.setGovernor(governor1);
        governor1.getLandlords().add(landlord2);
        landlord3.setGovernor(governor2);
        governor2.getLandlords().add(landlord3);
        landlord4.setGovernor(governor2);
        governor2.getLandlords().add(landlord4);
        landlord5.setGovernor(governor3);
        governor3.getLandlords().add(landlord5);
        landlord6.setGovernor(governor4);
        governor4.getLandlords().add(landlord6);
    }
    private void KingdomCreate(){
        kingdom1 =  Kingdom.builder()
                .name("Alrmeri dominion")
                .color(Arrays.asList(255,255,255))
                .coordinates(new Coordinates(123,23, LatDir.N, LongDir.E))
                .ruler(ruler1)
                .build();
        kingdom2 =  Kingdom.builder()
                .name("Daggerfall covenant")
                .color(Arrays.asList(255,255,255))
                .coordinates(new Coordinates(123,23, LatDir.N, LongDir.E))
                .ruler(ruler2)
                .build();
        kingdom3 =  Kingdom.builder()
                .name("Ebonheart pact")
                .color(Arrays.asList(255,255,255))
                .coordinates(new Coordinates(123,23, LatDir.N, LongDir.E))
                .ruler(ruler3)
                .build();
    }

    private void RegionCreate(){
        region1 = RegionImpl.builder()
                .name("Hjaalmarch")
                .color(Arrays.asList(0,-100,500))
                .climate("mountain")
                .coordinates(new Coordinates(123,23, LatDir.N, LongDir.E))
                .governor(governor1)
                .kingdom(kingdom3)
                .build();
        region2 = RegionImpl.builder()
                .name("Rift")
                .color(Arrays.asList(0,-100,500))
                .climate("mountain")
                .coordinates(new Coordinates(123,23, LatDir.N, LongDir.E))
                .governor(governor2)
                .kingdom(kingdom3)
                .build();
        region3 = RegionImpl.builder()
                .name("Greenshade")
                .color(Arrays.asList(0,-100,500))
                .climate("mountain")
                .coordinates(new Coordinates(123,23, LatDir.N, LongDir.E))
                .governor(governor3)
                .kingdom(kingdom1)
                .build();

        region4 = RegionImpl.builder()
                .name("Watch")
                .color(Arrays.asList(0,-100,500))
                .climate("mountain")
                .coordinates(new Coordinates(123,23, LatDir.N, LongDir.E))
                .governor(governor4)
                .kingdom(kingdom2)
                .build();
        regions = Arrays.asList(region1,region2,region3,region4);

    }

    private void RandomizeConstruction(){
        for (RegionImpl r: regions) {
            for (int i = 0; i < (int)(Math.random()*15+5); i++) {
                Construction construction = Construction.builder()
                        .name(names.get((int)(Math.random()*names.size())))
                        .color(Arrays.asList(0, -100, 500))
                        .coordinates(new Coordinates((float)Math.random()*180, (float)Math.random()*180, LatDir.values()[(int)(Math.random() * LatDir.values().length)], LongDir.values()[(int)(Math.random() * LongDir.values().length)]))
                        .constructionDate(LocalDate.now())
                        .constructionStatus(ConstructionStatus.values()[(int)(Math.random() * ConstructionStatus.values().length)])
                        .constructionType(ConstructionType.values()[(int)(Math.random() * ConstructionType.values().length)])
                        .landlord(landlord1)
                        .region(r)
                        .build();
                constructionRepository.save(construction);
                RandomizeBuilding(construction);
            }
        }
    }

    private void RandomizeBuilding(Construction c){
        for (int i = 0; i < 15; i++) {
            Building building = Building.builder()
                    .name("Building" + i)
                    .buildingType(BuildingType.values()[(int)(Math.random()*BuildingType.values().length)])
                    .construction(c)
                    .build();
            buildingRepository.save(building);
        }
    }

    private void SaveData(){

        //rulers
        rulerRepository.save(ruler1);
        rulerRepository.save(ruler2);
        rulerRepository.save(ruler3);
        //kingdoms
        kingdomRepository.save(kingdom1);
        kingdomRepository.save(kingdom2);
        kingdomRepository.save(kingdom3);
        //governors
        governorRepository.save(governor1);
        governorRepository.save(governor2);
        governorRepository.save(governor3);
        governorRepository.save(governor4);
        //governingContracts
        governingContractRepository.save(contract1);
        governingContractRepository.save(contract2);
        governingContractRepository.save(contract3);
        governingContractRepository.save(contract4);
        //landlords
        landlordRepository.save(landlord1);
        landlordRepository.save(landlord2);
        landlordRepository.save(landlord3);
        landlordRepository.save(landlord4);
        landlordRepository.save(landlord5);
        landlordRepository.save(landlord6);
        //regions
        regionRepository.save(region1);
        regionRepository.save(region2);
        regionRepository.save(region3);
        regionRepository.save(region4);
        //constructions

    }
}
