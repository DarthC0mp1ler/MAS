package pja.edu.pl.darth.c0mp1ler.models.dynamic;

import org.junit.Test;
import pja.edu.pl.darth.c0mp1ler.exceptions.ContentViolationException;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class DynamicTest {

    @Test
    public void test(){

        Person landLord = new Person("n","f","c");

        int counter = 0;
        landLord.getManagingConstruction();

        try{
            landLord.getGoverningRegionID();
        }catch (ContentViolationException e) {
            counter++;
        }

        landLord.becomeGovernor(5L);
        landLord.getGoverningRegionID();
        try{
            landLord.getManagingConstruction();
        }catch (ContentViolationException e) {
            counter++;
        }
        if(counter != 2){
            fail();
        }
    }
}
