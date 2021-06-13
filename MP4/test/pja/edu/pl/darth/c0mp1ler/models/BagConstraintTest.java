package pja.edu.pl.darth.c0mp1ler.models;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class BagConstraintTest {

    private Ruler ruler1;
    private Governor governor1;

    @Before
    public void setUp(){
        ruler1 = new Ruler("name","fa", LocalDate.now());
        governor1 = new Governor("name","d");
    }
    @Test
    public void test(){
        GoverningContract contract = new GoverningContract(LocalDate.now(),50,ruler1,governor1);
        GoverningContract contract1 = new GoverningContract(LocalDate.now(),50,ruler1,governor1);

        assertEquals(2,ruler1.getContracts().size());
        assertEquals(2,governor1.getContracts().size());

        contract.remove();
        assertEquals(1,ruler1.getContracts().size());
        assertEquals(1,governor1.getContracts().size());
    }

}
