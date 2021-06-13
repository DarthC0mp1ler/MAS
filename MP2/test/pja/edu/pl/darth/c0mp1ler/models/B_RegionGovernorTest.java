package pja.edu.pl.darth.c0mp1ler.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class B_RegionGovernorTest {

    private Governor governor1;
    private Governor governor2;
    private Region region1;
    private Region region2;
    private Region region3;
    private Region region4;

    @Before
    public void SetUp(){
        governor1 = new Governor("Governor1","FathersName");
        governor2 = new Governor("Governor2","FathersName");
        region1 = new Region("Region1",10,governor1);
        region2 = new Region("Region2",10,governor1);
        region3 = new Region("Region3",10,governor1);
        region4 = new Region("Region4",10,governor1);
    }

    @Test
    public void CreationTest() {
        assertEquals(governor1,region1.getGovernor());
        assertEquals(governor1,region2.getGovernor());
        assertEquals(governor1,region3.getGovernor());
        assertEquals(governor1,region4.getGovernor());

        assertTrue(governor1.getRegions().contains(region1));
        assertTrue(governor1.getRegions().contains(region2));
        assertTrue(governor1.getRegions().contains(region3));
        assertTrue(governor1.getRegions().contains(region4));

        assertEquals(0,governor2.getRegions().size());
    }

    @Test
    public void SetGovernorTest() {
        assertEquals(governor1,region1.getGovernor());
        assertTrue(governor1.getRegions().contains(region1));
        assertFalse(governor2.getRegions().contains(region1));

        region1.setGovernor(governor2);

        assertEquals(governor2,region1.getGovernor());
        assertFalse(governor1.getRegions().contains(region1));
        assertTrue(governor2.getRegions().contains(region1));
    }

    @Test
    public void AddRegionTest() {
        assertEquals(governor1,region1.getGovernor());
        assertTrue(governor1.getRegions().contains(region1));
        assertFalse(governor2.getRegions().contains(region1));

        governor2.addRegion(region1);

        assertEquals(governor2,region1.getGovernor());
        assertFalse(governor1.getRegions().contains(region1));
        assertTrue(governor2.getRegions().contains(region1));
    }

}
