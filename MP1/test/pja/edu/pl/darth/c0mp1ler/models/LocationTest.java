package pja.edu.pl.darth.c0mp1ler.models;

import org.junit.Before;
import org.junit.Test;
import pja.edu.pl.darth.c0mp1ler.exceptions.ContentViolationException;
import pja.edu.pl.darth.c0mp1ler.exceptions.NullValidationException;

import static org.junit.Assert.*;

public class LocationTest {

    private final String kingdom1 = "Ebonheart Pact";
    private final String kingdom2 = "Aldmeri Dominion";
    private final String region1 = "Skyrim";
    private final String holding1 = "Eastmarch";
    private final String holding2 = "The Rift";;

    private final String region2 = "Morrowind";
    private final String holding3 = "Ascadian Isles";

    private final String ownerName1 = "Ulfrik the Stormcloak";
    private final String ownerName2 = "Ayrenn Arana Aldmeri";
    private final String ownerName3 = "Vivec the Warrior-Poet";

    private final String resource1 = "Wood";
    private final String resource2 = "Fish";
    private final String resource3 = "Stone";
    private final String resource4 = "Copper";

    private Location loc1;
    private Location loc2;
    private Location loc3;
    private Location loc4;

    @Before
    public void SetUp() {
        loc1 = new Location(kingdom1, region1, holding1, resource1, ownerName1);
        loc2 = new Location(kingdom1, region1, holding2, resource2, ownerName2);
        loc3 = new Location(kingdom1, region2, holding3, resource4, ownerName3);
        loc4 = new Location(kingdom2, region1, holding3, resource3);
    }

    @Test
    public void LocationCreationTest() {
        assertNotNull(loc1);
        assertNotNull(loc2);
        assertNotNull(loc3);
        assertNotNull(loc4);
    }

    @Test
    public void LocationGettersTest() {
        assertEquals(loc1.getKingdom(), kingdom1);
        assertEquals(loc2.getKingdom(), kingdom1);

        assertEquals(loc1.getRegion(), region1);
        assertEquals(loc3.getRegion(), region2);

        assertEquals(loc1.getHolding(), holding1);
        assertEquals(loc3.getHolding(), holding3);

        assertEquals(loc1.getOwnerName(), ownerName1);
        assertNull(loc4.getOwnerName());

        assertEquals(loc1.getResources().toArray()[0], resource1);
        assertEquals(loc2.getResources().toArray()[0], resource2);

    }

    @Test(expected = NullValidationException.class)
    public void SetKingdomTest() {
        assertEquals(loc1.getKingdom(), kingdom1);
        assertEquals(loc2.getKingdom(), kingdom1);

        loc1.setKingdom(kingdom2);
        assertEquals(loc1.getKingdom(), kingdom2);

        loc2.setKingdom(null);
    }

    @Test(expected = NullValidationException.class)
    public void SetHoldingTest() {
        assertEquals(loc1.getHolding(), holding1);
        assertEquals(loc2.getHolding(), holding2);

        loc1.setHolding(holding3);
        assertEquals(loc1.getHolding(), holding3);

        loc2.setKingdom("    ");
    }

    @Test(expected = NullValidationException.class)
    public void SetRegionTest() {
        assertEquals(loc1.getRegion(), region1);
        assertEquals(loc2.getRegion(), region1);

        loc1.setRegion(region2);
        assertEquals(loc1.getRegion(), region2);

        loc2.setKingdom("    ");
    }

    @Test
    public void SetOwnerNameTest() {
        assertEquals(loc1.getOwnerName(),ownerName1);

        loc1.setOwnerName(null);

        assertNull(loc1.getOwnerName());

        loc1.setOwnerName(ownerName2);
        assertEquals(loc1.getOwnerName(),ownerName2);
    }

    @Test(expected = NullValidationException.class)
    public void AddResourceTest() {
        assertTrue(loc1.getResources().contains(resource1));
        assertTrue(loc2.getResources().contains(resource2));

        loc1.addResource(resource2);
        assertTrue(loc1.getResources().contains(resource2));
        assertEquals(loc1.getResources().size(),2);

        loc2.addResource(null);
    }

    @Test(expected = NullValidationException.class)
    public void RemoveResourceTestOnNull() {
        assertEquals(loc1.getResources().toArray()[0], resource1);
        loc1.addResource(resource2);
        assertTrue(loc1.getResources().contains(resource2));
        assertEquals(loc1.getResources().size(),2);
        loc1.removeResources(resource2);
        assertFalse(loc1.getResources().contains(resource2));

        loc1.removeResources(null);
    }

    @Test(expected = ContentViolationException.class)
    public void RemoveResourceTestOnEmptyList() {
        assertEquals(loc1.getResources().toArray()[0], resource1);
        loc1.addResource(resource2);
        assertTrue(loc1.getResources().contains(resource2));
        assertEquals(loc1.getResources().size(),2);
        loc1.removeResources(resource2);
        assertFalse(loc1.getResources().contains(resource2));

        loc1.removeResources(resource1);
    }

}
