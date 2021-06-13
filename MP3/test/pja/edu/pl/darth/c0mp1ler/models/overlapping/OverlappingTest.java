package pja.edu.pl.darth.c0mp1ler.models.overlapping;

import org.junit.Test;
import pja.edu.pl.darth.c0mp1ler.exceptions.ContentViolationException;

import static org.junit.Assert.*;

public class OverlappingTest {

    @Test
    public void test(){

        Person landLord = new Person("n","f","c");
        Person governor = new Person("n","f",1L);
        Person landGov = new Person("n","f",5L,"c");

        int counter = 0;

        try{
            landLord.getGoverningRegionID();
        }catch (ContentViolationException e) {
            counter++;
        }

        try{
            governor.getManagingConstruction();
        }catch (ContentViolationException e) {
            counter++;
        }
        if(counter != 2){
            fail();
        }
        landGov.getManagingConstruction();
        landGov.getGoverningRegionID();
    }

}
