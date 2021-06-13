package pja.edu.pl.darth.c0mp1ler.models;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class XORConstraintTest {


    private Governor governor1;
    private Region region1;
    private Kingdom kingdom1;

    @Test
    public void test(){
        governor1 = new Governor("d","g");
        region1 = new Region("dg");
        kingdom1 = new Kingdom("gfdg",new Ruler("gfd","gf",LocalDate.now()));

        governor1.setRegion(region1);
        assertNull(kingdom1.getAdvisor());
        assertNull(governor1.getKingdom());
        assertEquals(region1,governor1.getRegion());

        governor1.setKingdom(kingdom1);
        assertNull(region1.getGovernor());
        assertNull(governor1.getRegion());
        assertEquals(kingdom1,governor1.getKingdom());

        governor1.setRegion(region1);
        assertNull(kingdom1.getAdvisor());
        assertNull(governor1.getKingdom());
        assertEquals(region1,governor1.getRegion());
    }



}
