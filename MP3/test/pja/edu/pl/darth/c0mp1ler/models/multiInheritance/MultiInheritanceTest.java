package pja.edu.pl.darth.c0mp1ler.models.multiInheritance;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MultiInheritanceTest {

    @Test
    public void test(){
        List<Region> regions = new ArrayList<>();
        List<Construction> constructions = new ArrayList<>();

        Capital capital = new Capital("n",ConstructionType.Castle, LocalDate.now(),"r",4);

        regions.add(capital);
        constructions.add(capital);

        assertTrue(regions.contains(capital));
        assertTrue(constructions.contains(capital));
    }

}
