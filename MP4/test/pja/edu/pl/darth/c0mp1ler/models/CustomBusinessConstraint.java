package pja.edu.pl.darth.c0mp1ler.models;

import org.junit.Before;
import org.junit.Test;
import pja.edu.pl.darth.c0mp1ler.exceptions.ContentViolationException;

import java.time.LocalDate;

import static org.junit.Assert.*;
public class CustomBusinessConstraint {

    private Ruler ruler1;
    private Ruler ruler2;
    private Kingdom kingdom1;
    private Regent regent1;
    private Regent regent2;

    @Before
    public void setUp(){
        ruler1 = new Ruler("name","fa", LocalDate.now());
        ruler2 = new Ruler("name","fa", LocalDate.of(1990,4,3));

        kingdom1 = new Kingdom("name",ruler1);
        regent1 = new Regent("regent");
        regent2 = new Regent("regent");
    }

    @Test
    public void test(){
        kingdom1.setRegent(regent1);
        assertEquals(regent1,kingdom1.getRegent());
        assertEquals(kingdom1,regent1.getKingdom());
        kingdom1.setRegent(regent2);
        assertEquals(regent2,kingdom1.getRegent());
        assertEquals(kingdom1,regent2.getKingdom());
        regent1.setKingdom(kingdom1);
        assertEquals(regent2,kingdom1.getRegent());
        assertEquals(kingdom1,regent2.getKingdom());

        kingdom1.setRuler(ruler2);
        assertEquals(ruler2,kingdom1.getRuler());
        assertNull(kingdom1.getRegent());

        kingdom1.setRegent(regent2);
        assertNull(kingdom1.getRegent());
    }
}
