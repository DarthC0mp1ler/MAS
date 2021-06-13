package pja.edu.pl.darth.c0mp1ler.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import pja.edu.pl.darth.c0mp1ler.exceptions.ContentViolationException;
import pja.edu.pl.darth.c0mp1ler.typeEnums.BuildingType;
import pja.edu.pl.darth.c0mp1ler.typeEnums.ConstructionType;

import java.time.LocalDate;

public class C_ConstructionBuilding {

    private Construction construction1;
    private Construction construction2;

    private Building building1;
    private Building building2;
    private Building building3;
    private Building building4;

    @Before
    public void SetUp(){
        construction1 = new Construction("Constr1", ConstructionType.Castle, LocalDate.now());
        construction2 = new Construction("Constr2", ConstructionType.Castle, LocalDate.now());

        building1 = Building.createBuilding("building1", BuildingType.Church,construction1);
        building2 = Building.createBuilding("building2", BuildingType.Forge,construction1);
        building3 = Building.createBuilding("building3", BuildingType.Manor,construction2);
        building4 = Building.createBuilding("building4", BuildingType.House,construction1);
    }

    @Test
    public void CreationTest(){
        assertEquals(3,construction1.getBuildings().size());
        assertTrue(construction1.getBuildings().contains(building1));
        assertTrue(construction1.getBuildings().contains(building2));
        assertTrue(construction1.getBuildings().contains(building4));

        assertEquals(1,construction2.getBuildings().size());
        assertTrue(construction2.getBuildings().contains(building3));
    }

    @Test
    public void SwapTryTest(){
        try {
            construction1.addBuilding(building3);
        } catch (ContentViolationException e) {
            return;
        }
        fail();
    }

    @Test
    public void DeleteBuildingTest(){
        building1.delete();
        assertEquals(2,construction1.getBuildings().size());
        assertFalse(construction1.getBuildings().contains(building1));
    }

    @Test
    public void DeleteConstructionTest(){
        assertEquals(3,construction1.getBuildings().size());
        construction1.delete();
        assertEquals(0,construction1.getBuildings().size());
        assertNull(building1.getConstruction());
        assertNull(building2.getConstruction());
        assertNull(building4.getConstruction());
    }

}
