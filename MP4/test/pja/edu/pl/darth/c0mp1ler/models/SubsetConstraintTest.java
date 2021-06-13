package pja.edu.pl.darth.c0mp1ler.models;

import org.junit.Before;
import org.junit.Test;
import pja.edu.pl.darth.c0mp1ler.exceptions.ContentViolationException;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class SubsetConstraintTest {

    private Landlord landlord;
    private Landlord landlord1;
    private Fraction fraction;

    @Test
    public void test(){
        landlord = new Landlord("l","d");
        landlord1 = new Landlord("l","d");

        fraction = new Fraction("f",landlord);
        assertEquals(landlord,fraction.getLeader());
        assertEquals(landlord,fraction.getRebels().get(0));
        assertEquals(1,fraction.getRebels().size());

        fraction.addRebel(landlord1);
        assertEquals(landlord,fraction.getLeader());
        assertEquals(landlord,fraction.getRebels().get(0));
        assertEquals(landlord1,fraction.getRebels().get(1));
        assertEquals(2,fraction.getRebels().size());

        fraction.makeLeader(landlord1);
        assertEquals(landlord1,fraction.getLeader());
        assertEquals(landlord1,fraction.getRebels().get(0));
        assertEquals(landlord,fraction.getRebels().get(1));
        assertEquals(2,fraction.getRebels().size());

    }

}
