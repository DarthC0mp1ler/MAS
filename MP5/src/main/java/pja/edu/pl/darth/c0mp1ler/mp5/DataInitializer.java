package pja.edu.pl.darth.c0mp1ler.mp5;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pja.edu.pl.darth.c0mp1ler.mp5.model.Construction;
import pja.edu.pl.darth.c0mp1ler.mp5.model.ConstructionType;
import pja.edu.pl.darth.c0mp1ler.mp5.model.Governor;
import pja.edu.pl.darth.c0mp1ler.mp5.model.Landlord;
import pja.edu.pl.darth.c0mp1ler.mp5.repository.ConstructionRepository;
import pja.edu.pl.darth.c0mp1ler.mp5.repository.GovernorRepository;
import pja.edu.pl.darth.c0mp1ler.mp5.repository.LandlordRepository;
import pja.edu.pl.darth.c0mp1ler.mp5.repository.PersonRepository;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final ConstructionRepository constructionRepository;

    private final PersonRepository personRepository;
    private final GovernorRepository governorRepository;
    private final LandlordRepository landlordRepository;

    @EventListener
    public void atStart(ContextRefreshedEvent evt){
        System.out.println("Program started");
        setConstructionRepository();
        setPeopleRepository();
    }

    private void setConstructionRepository(){
        Construction c = Construction.builder()
                .name("Alinor")
                .constructionDate(LocalDate.of(1178,8,4))
                .constructionType(ConstructionType.City)
                .build();
        constructionRepository.save(c);
        c = Construction.builder()
                .name("Solitude")
                .constructionDate(LocalDate.of(1554,12,7))
                .constructionType(ConstructionType.Fortress)
                .build();
        constructionRepository.save(c);

    }

    private void setPeopleRepository(){
        Governor g = Governor.builder()
                .name("Ulfric")
                .fathersName("Fatherson")
                .governingRegionName("Eastmarch")
                .build();
        governorRepository.save(g);
        g = Governor.builder()
                .name("Ayrenn")
                .fathersName("Fatherdaughter")
                .governingRegionName("Summerset")
                .build();
        governorRepository.save(g);
        Landlord l = Landlord.builder()
                .name("Geralt")
                .fathersName("Vesemirson")
                .wealth(1435)
                .build();
        landlordRepository.save(l);
    }

}
