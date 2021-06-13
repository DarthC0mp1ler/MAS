package pja.edu.pl.darth.c0mp1ler.models.standard;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StandardTest {

    @Test
    public void test(){
        List<Building> buildings = new ArrayList<>();

        Farm farm = new Farm("f","o","f");
        farm.addPossibleResources("crops");
        Forge forge =new Forge("f","o","s");
        forge.addThingsToRepair("Hammer");
        forge.addThingsToRepair("Hammer");
        forge.addThingsToRepair("Hammer");
        forge.addThingsToRepair("Axe");

        buildings.add(farm);
        buildings.add(forge);

        for (Building b: buildings) {
            b.operate();
        }

        assertTrue(farm.getPossibleResources().contains("crops"));
        assertTrue(farm.getProducedResources().contains("crops"));

        assertEquals(1,farm.getProducedResources().size());

        assertEquals(3,forge.getThingsToRepair().size());
        assertEquals(1,forge.getRepairedThings().size());

        buildings.get(0).retrieve("crops");
        buildings.get(1).retrieve("Hammer");

        assertFalse(farm.getProducedResources().contains("crops"));

        assertEquals(0,farm.getProducedResources().size());

        assertEquals(3,forge.getThingsToRepair().size());
        assertEquals(0,forge.getRepairedThings().size());

    }

}
