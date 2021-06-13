package pja.edu.pl.darth.c0mp1ler.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import pja.edu.pl.darth.c0mp1ler.typeEnums.ConstructionType;

import java.time.LocalDate;

public class Q_RegionConstructionTest {

    private Governor governor1;
    private Region region1;
    private Region region2;

    private Construction construction1;
    private Construction construction2;

    @Before
    public void SetUp(){
        governor1 = new Governor("Governor1","FathersName");
        region1 = new Region("Region1",10,governor1);
        region2 = new Region("Region2",6,governor1);

        construction1 = new Construction("Constr1", ConstructionType.Castle, LocalDate.now());
        construction2 = new Construction("Constr2", ConstructionType.Castle, LocalDate.now());
    }

    @Test
    public void SetAssociationTest(){
        construction1.setRegion(region1);
        assertEquals(region1,construction1.getRegion());
        assertEquals(construction1,region1.getBelongingsList().get(0));

        region2.addBelonging(construction2);
        assertEquals(construction2,region2.getBelongingsList().get(0));
        assertEquals(region2,construction2.getRegion());

    }

    @Test
    public void SwapAssociationTest(){
        construction1.setRegion(region1);
        assertEquals(region1,construction1.getRegion());
        assertEquals(construction1,region1.getBelongingsList().get(0));

        construction1.setRegion(region2);
        assertEquals(region2,construction1.getRegion());
        assertEquals(construction1,region2.getBelongingsList().get(0));
        assertEquals(0,region1.getBelongingsList().size());

        region1.addBelonging(construction1);
        assertEquals(construction1,region1.getBelongingsList().get(0));
        assertEquals(region1,construction1.getRegion());
        assertEquals(0,region2.getBelongingsList().size());

    }

    @Test
    public void RemoveAssociationTest(){
        construction1.setRegion(region1);
        assertEquals(region1,construction1.getRegion());
        assertEquals(construction1,region1.getBelongingsList().get(0));

        construction1.setRegion(null);
        assertNull(construction1.getRegion());
        assertEquals(0,region1.getBelongingsList().size());

        region2.addBelonging(construction2);
        assertEquals(construction2,region2.getBelongingsList().get(0));
        assertEquals(region2,construction2.getRegion());

        region2.removeBelonging(construction2);
        assertNull(construction2.getRegion());
        assertEquals(0,region2.getBelongingsList().size());
    }


}
