package pja.edu.pl.darth.c0mp1ler.models.multi_aspectInheritance;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;

public class MultiAspectTest {

    @Test
    public void test(){

        DevelopmentDirection d1 = new EconomyBased(4);
        DevelopmentDirection d2 = new MilitaryBased(3);

        Construction construction = new City("s", LocalDate.now(),d1,1);
        assertEquals(d1, construction.getDirection());
        assertEquals(construction,d1.getConstruction());

        construction.setDirection(d2);
        assertEquals(d2, construction.getDirection());
        assertNull(d1.getConstruction());
        assertEquals(construction,d2.getConstruction());

    }

}
