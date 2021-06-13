package pja.edu.pl.darth.c0mp1ler.models;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import pja.edu.pl.darth.c0mp1ler.exceptions.NullValidationException;
import pja.edu.pl.darth.c0mp1ler.typeEnums.ConstructionType;

import java.time.LocalDate;

public class A_GoverningContractTest {

    private Governor governor1;
    private Governor governor2;

    private Landlord landlord1;
    private Landlord landlord2;
    private Landlord landlord3;
    private Landlord landlord4;

    private GoverningContract contract1;
    private GoverningContract contract2;
    private GoverningContract contract3;
    private GoverningContract contract4;

    @Before
    public void SetUp(){
        governor1 = new Governor("Governor1","FathersName");
        governor2 = new Governor("Governor2","FathersName");
        landlord1 = new Landlord("Landlord1","FathersName",new Construction("m", ConstructionType.City,LocalDate.now()));
        landlord2 = new Landlord("Landlord2","FathersName",new Construction("m", ConstructionType.City,LocalDate.now()));
        landlord3 = new Landlord("Landlord3","FathersName",new Construction("m", ConstructionType.City,LocalDate.now()));
        landlord4 = new Landlord("Landlord4","FathersName",new Construction("m", ConstructionType.City,LocalDate.now()));

        contract1 = new GoverningContract(LocalDate.now(),10,governor1,landlord1);
        contract2 = new GoverningContract(LocalDate.now(),5,governor1,landlord2);
        contract3 = new GoverningContract(LocalDate.now(),6,governor1,landlord3);
        contract4 = new GoverningContract(LocalDate.now(),7,governor2,landlord4);
    }

    @Test
    public void creationTest() {
        assertEquals(governor1,contract1.getGovernor());
        assertEquals(landlord1,contract1.getLandlord());

        assertEquals(contract1,landlord1.getContract());
        assertTrue(governor1.getGoverningContracts().contains(contract1));

        int counter = 0;

        GoverningContract tmp = null;
        try{
            tmp = new GoverningContract(LocalDate.now(),10,governor1,null);
        }catch (NullValidationException e) {
            counter ++;
        }

        assertFalse(governor1.getGoverningContracts().contains(tmp));

        try{
            tmp = new GoverningContract(LocalDate.now(),10,null,landlord1);
        }catch (NullValidationException e) {
            counter ++;
        }
        assertNotEquals(tmp,landlord1.getContract());
        assertEquals(contract1,landlord1.getContract());

        if(counter != 2) {
            fail();
        }

        assertEquals(3,governor1.getGoverningContracts().size());
        assertEquals(1,governor2.getGoverningContracts().size());
    }

    @Test
    public void changeAssociationTest(){
        GoverningContract tmp = new GoverningContract(LocalDate.now(),5,governor2,landlord1);

        assertEquals(2,governor1.getGoverningContracts().size());
        assertEquals(2,governor2.getGoverningContracts().size());

        assertEquals(governor2,tmp.getGovernor());
        assertEquals(landlord1,tmp.getLandlord());

        assertEquals(tmp,landlord1.getContract());
        assertTrue(governor2.getGoverningContracts().contains(tmp));

        assertNotEquals(governor1,contract1.getGovernor());
        assertNotEquals(landlord1,contract1.getLandlord());

        assertNotEquals(contract1,landlord1.getContract());
        assertFalse(governor1.getGoverningContracts().contains(contract1));

        GoverningContract tmp2 = new GoverningContract(LocalDate.now(),5,governor1,landlord1);

        assertEquals(3,governor1.getGoverningContracts().size());
        assertEquals(1,governor2.getGoverningContracts().size());
    }

    @Test
    public void removeAssociationTest() {
        contract1.remove();

        assertNotEquals(governor1,contract1.getGovernor());
        assertNotEquals(landlord1,contract1.getLandlord());

        assertNotEquals(contract1,landlord1.getContract());
        assertFalse(governor1.getGoverningContracts().contains(contract1));
    }

}
