package pja.edu.pl.darth.c0mp1ler.models;

import org.junit.Before;
import org.junit.Test;
import pja.edu.pl.darth.c0mp1ler.exceptions.ContentViolationException;
import pja.edu.pl.darth.c0mp1ler.exceptions.NullValidationException;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ConstructionTest {

    private final String kingdom1 = "Ebonheart Pact";
    private final String kingdom2 = "Aldmeri Dominion";
    private final String region1 = "Skyrim";
    private final String holding1 = "Eastmarch";
    private final String constrName1 = "Windhelm";
    private final String holding2 = "The Rift";
    private final String constrName2 = "Riften";

    private final String region2 = "Morrowind";
    private final String holding3 = "Ascadian Isles";
    private final String constrName3 = "Vivec";

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

    private Construction contr1;
    private Construction contr2;
    private Construction contr3;

    private LocalDate constrDate;
    private LocalDate destrDate;

    private int localsNo1 = 2100;
    private int localsNo2 = 5400;
    private int localsNo3 = 3200;
    private String protection = "Fort";

    @Before
    public void setUp(){
        loc1 = new Location(kingdom1, region1, holding1, resource1, ownerName1);
        loc2 = new Location(kingdom2, region1, holding2, resource2, ownerName2);
        loc3 = new Location(kingdom1, region2, holding3, resource4, ownerName3);

        constrDate = LocalDate.of(2000, 1, 8);
        destrDate = LocalDate.of(2010, 4, 23);

        contr1 = new Construction(constrName1, ConstructionType._CASTLE_, constrDate, localsNo1, loc1, ConstructionStatus._STANDARD_, 10);
        contr2 = new Construction(constrName2, ConstructionType._TEMPLE_, constrDate, localsNo2, loc2, ConstructionStatus._INSPIRED_, 20, protection);
        contr3 = new Construction(constrName3, ConstructionType._VILLAGE_, constrDate, localsNo3, destrDate, loc2, ConstructionStatus._DESTRUCTED_, 30);
    }

    @Test(expected = NullValidationException.class)
    public void setName() {
        assertEquals(constrName1, contr1.getName());
        assertNotEquals(constrName2, contr1.getName());
        contr1.setName(constrName2);
        assertEquals(constrName2, contr1.getName());
        contr1.setName("   ");
    }

    @Test(expected = NullValidationException.class)
    public void setConstructionType() {
        assertEquals(ConstructionType._CASTLE_, contr1.getConstructionType());
        contr1.setConstructionType(ConstructionType._CITY_);
        assertEquals(ConstructionType._CITY_, contr1.getConstructionType());
        assertTrue(contr1.getConstructionDate().compareTo(constrDate) > 0);

        contr1.setConstructionType(null);
    }

    @Test(expected = NullValidationException.class)
    public void setConstructionDate() {
        assertEquals(0, contr1.getConstructionDate().compareTo(constrDate));
        contr1.setConstructionDate(LocalDate.now());
        assertTrue(contr1.getConstructionDate().compareTo(constrDate) > 0);

        contr1.setConstructionDate(null);
    }

    @Test(expected = ContentViolationException.class)
    public void setLocalsNo() {
        assertEquals(localsNo1, contr1.getLocalsNo());
        contr1.setLocalsNo(localsNo2);
        assertEquals(localsNo2, contr1.getLocalsNo());

        contr2.setLocalsNo(-1);
    }

    @Test
    public void setDestructionDate() {
        assertEquals(destrDate, contr3.getDestructionDate());
        contr3.setDestructionDate(LocalDate.now());
        assertEquals(LocalDate.now(), contr3.getDestructionDate());
        contr3.setDestructionDate(null);
        assertNull(contr3.getDestructionDate());
    }

    @Test(expected = NullValidationException.class)
    public void addProtection() {
        final String protection = "Pikes";
        assertEquals(1, contr2.getProtection().size());
        assertFalse(contr2.getProtection().contains(protection));
        contr2.addProtection(protection);
        assertEquals(2, contr2.getProtection().size());
        assertTrue(contr2.getProtection().contains(protection));
        contr2.addProtection(null);
    }

    @Test(expected = NullValidationException.class)
    public void removeProtection() {
        final String protection = "Pikes";
        contr2.addProtection(protection);
        assertEquals(2, contr2.getProtection().size());
        assertTrue(contr2.getProtection().contains(protection));
        contr2.removeProtection(protection);
        assertEquals(1, contr2.getProtection().size());
        assertFalse(contr2.getProtection().contains(protection));

        contr2.removeProtection(null);
    }

    @Test(expected = NullValidationException.class)
    public void setLocation() {
        assertEquals(loc1, contr1.getLocation());
        contr2.setLocation(loc2);
        assertEquals(loc2, contr2.getLocation());

        contr2.setLocation(null);

    }

    @Test
    public void addDiscoveredResources() {
        assertEquals(0, contr2.getDiscoveredResources().size());
        assertFalse(contr2.getDiscoveredResources().contains(resource2));
        contr2.addDiscoveredResources(resource2);
        assertEquals(1, contr2.getDiscoveredResources().size());
        assertTrue(contr2.getDiscoveredResources().contains(resource2));
        int caught = 0;
        try {
            contr2.addDiscoveredResources("Iron");
        } catch (ContentViolationException e) {
            caught++;
        }
        try {
            contr2.addDiscoveredResources(null);
        } catch (NullValidationException e) {
            caught++;
        }
        assertEquals(2, caught);

    }

    @Test(expected = NullValidationException.class)
    public void removeDiscoveredResources() {
        contr2.addDiscoveredResources(resource2);
        assertEquals(1, contr2.getDiscoveredResources().size());
        assertTrue(contr2.getDiscoveredResources().contains(resource2));
        contr2.removeDiscoveredResources(resource2);
        assertEquals(0, contr2.getDiscoveredResources().size());
        assertFalse(contr2.getDiscoveredResources().contains(resource2));

        contr2.removeDiscoveredResources(null);
    }

    @Test
    public void setStatus() {
        assertEquals(ConstructionStatus._STANDARD_,contr1.getStatus());
        contr1.setStatus(ConstructionStatus._RAIDED_);
        assertEquals(ConstructionStatus._RAIDED_,contr1.getStatus());
        contr1.setStatus(ConstructionStatus._DESTRUCTED_);
        assertEquals(ConstructionStatus._DESTRUCTED_,contr1.getStatus());
        assertNotNull(contr1.getDestructionDate());
    }

    @Test
    public void setWealth() {
        float wealth = 15.3f;
        assertTrue(10.f == contr1.getWealth());
        contr1.setWealth(wealth);
        assertTrue(wealth == contr1.getWealth());
    }

    @Test
    public void getProfit() {
        assertTrue(contr1.getWealth() == contr1.getProfit());
        contr1.setStatus(ConstructionStatus._RAIDED_);
        assertTrue(contr1.getWealth() - (contr1.getWealth()/2) == contr1.getProfit());
        contr1.setStatus(ConstructionStatus._PROSPEROUS_);
        assertTrue(contr1.getWealth() + contr1.getWealth() == contr1.getProfit());
    }

    @Test
    public void getAge() {
        Period age = contr1.getAge();
        Period tAge = Period.between(constrDate, LocalDate.now());
        assertEquals(tAge.getDays(),age.getDays());
        assertEquals(tAge.getMonths(),age.getMonths());
        assertEquals(tAge.getYears(),age.getYears());
    }

    @Test(expected = NullValidationException.class)
    public void addDiscoveredProtectionStructures() {
        int size = Construction.getDiscoveredProtectionStructures().size();
        assertTrue(Construction.getDiscoveredProtectionStructures().contains(protection));
        Construction.addDiscoveredProtectionStructures("Moat");
        assertEquals(size+1,Construction.getDiscoveredProtectionStructures().size());
        assertTrue(Construction.getDiscoveredProtectionStructures().contains("Moat"));
        contr1.addProtection("Towers");
        assertEquals(size+2,Construction.getDiscoveredProtectionStructures().size());
        assertTrue(Construction.getDiscoveredProtectionStructures().contains("Towers"));
        Construction.addDiscoveredProtectionStructures(null);
    }

    @Test(expected = NullValidationException.class)
    public void removeDiscoveredProtectionStructures() {
        int size = Construction.getDiscoveredProtectionStructures().size();
        assertTrue(Construction.getDiscoveredProtectionStructures().contains(protection));
        Construction.removeDiscoveredProtectionStructures(protection);
        assertEquals(size-1,Construction.getDiscoveredProtectionStructures().size());
        assertFalse(Construction.getDiscoveredProtectionStructures().contains(protection));
        Construction.removeDiscoveredProtectionStructures(null);
    }

    @Test
    public void helpLocals() {
        assertEquals(ConstructionStatus._STANDARD_,contr1.getStatus());
        contr1.helpLocals();
        assertEquals(ConstructionStatus._INSPIRED_,contr1.getStatus());
        contr1.helpLocals();
        assertEquals(ConstructionStatus._PROSPEROUS_,contr1.getStatus());
        contr1.helpLocals();
        assertEquals(ConstructionStatus._PROSPEROUS_,contr1.getStatus());
    }

    @Test
    public void discoverResourceType() {
        assertEquals(0, contr1.getDiscoveredResources().size());
        assertFalse(contr1.getDiscoveredResources().contains(resource1));
        loc1.addResource(resource2);
        contr1.discoverResourceType();
        assertTrue(contr1.getDiscoveredResources().contains(resource1) || contr1.getDiscoveredResources().contains(resource2));
    }

    @Test
    public void raid() {
        assertEquals(ConstructionStatus._STANDARD_,contr1.getStatus());
        contr1.raid();
        assertEquals(ConstructionStatus._RAIDED_,contr1.getStatus());
        contr1.raid();
        assertEquals(ConstructionStatus._STARVING_,contr1.getStatus());
        contr1.raid();
        assertEquals(ConstructionStatus._DESTRUCTED_,contr1.getStatus());
        contr1.raid();
        assertEquals(ConstructionStatus._DESTRUCTED_,contr1.getStatus());
    }

    @Test
    public void destroyAllProtections() {
        contr2.addProtection(protection);
        contr2.addProtection("Walls");
        contr2.addProtection("Moat");
        assertEquals(4,contr2.getProtection().size());
        contr2.destroyAllProtections();
        assertEquals(0,contr2.getProtection().size());
    }

    @Test
    public void findConstructionsByLocation() {
        List<Construction> tList = Construction.findConstructionsByLocation(loc2);
        assertEquals(2,tList.size());
        assertTrue(tList.contains(contr3));
        assertTrue(tList.contains(contr2));
    }

    @Test
    public void findConstructionsByName() {
        List<Construction> tList = Construction.findConstructionsByName("Windhelm");
        assertTrue(tList.contains(contr1));
    }

    @Test
    public void findConstructionsByType() {
        List<Construction> tList = Construction.findConstructionsByType(ConstructionType._CASTLE_);
        assertTrue(tList.contains(contr1));
    }

    @Test
    public void writeAndReadExtent() {
        Construction.writeExtent();
        List<Construction> saved = new ArrayList<>(Construction.getExtent());
        Construction.readExtent();
        System.out.println(Construction.getExtent());
        System.err.println(Construction.getExtent().size());
        assertEquals(saved.size(),Construction.getExtent().size());
    }
}