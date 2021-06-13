package pja.edu.pl.darth.c0mp1ler.models;

import org.junit.Before;
import org.junit.Test;
import pja.edu.pl.darth.c0mp1ler.exceptions.ContentViolationException;

import java.time.LocalDate;

import static org.junit.Assert.*;
public class OrderedConstraintTest {

    private Governor governor1;
    private Landlord landlord;
    private Landlord landlord1;
    @Before
    public void setUp(){
        governor1 = new Governor("name","d");
        landlord = new Landlord("l","f");
        landlord1 = new Landlord("l","f");
    }

    @Test(expected = ContentViolationException.class)
    public void Test(){
        governor1.addSubordinate(landlord);
        governor1.addSubordinate(landlord1);
        assertEquals(2,governor1.getSubordinates().size());
        assertEquals(landlord,governor1.getSubordinates().get(0));
        assertEquals(landlord1,governor1.getSubordinates().get(1));

        governor1.addSubordinate(landlord1);
    }
}
