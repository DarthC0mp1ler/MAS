package pja.edu.pl.darth.c0mp1ler.models;

import org.junit.Before;
import org.junit.Test;
import pja.edu.pl.darth.c0mp1ler.exceptions.ContentViolationException;


public class UniqueConstraintTest {
    private Region region;
    private Region region1;


    @Before
    public void setUp(){
        region = new Region("re");
        region1 = new Region("ree");
    }
    @Test(expected = ContentViolationException.class)
    public void test(){
        new Region("re");
    }
}
