package pja.edu.pl.darth.c0mp1ler.models;

import pja.edu.pl.darth.c0mp1ler.exceptions.ContentViolationException;
import pja.edu.pl.darth.c0mp1ler.exceptions.NullValidationException;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class GoverningContract {

    private static final Set<GoverningContract> extent = new HashSet<>();

    private static final float taxChangeRate = 10;

    private LocalDate signDate;
    private LocalDate terminationDate;
    private float tax;

    private Ruler sovereign;
    private Governor vassal;

    public GoverningContract(LocalDate signDate, float tax, Ruler sovereign, Governor vassal) {
        setSignDate(signDate);
        setTax(tax);
        if( sovereign != null && vassal != null){
            setSovereign(sovereign);
            setVassal(vassal);
        }else {
            throw new NullValidationException("Either Sovereign or Vassal is empty");
        }
        extent.add(this);
    }

    public LocalDate getSignDate() {
        return signDate;
    }

    private void setSignDate(LocalDate signDate) {
        if (signDate == null) {
            throw new NullValidationException("Sign date cannot be null");
        }
        this.signDate = signDate;
    }

    public LocalDate getTerminationDate() {
        return terminationDate;
    }

    private void setTerminationDate(LocalDate terminationDate) {
        if (terminationDate == null) {
            throw new NullValidationException("Termination date cannot be null");
        }
        this.terminationDate = terminationDate;
    }

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        if (tax < 0) {
            throw new ContentViolationException("Tax cannot be negative");
        }
        if(this.tax != 0.f && tax - this.tax > this.tax*taxChangeRate/100f){
            throw new ContentViolationException("Tax cannot be changed by more than 10% at once");
        }
        this.tax = tax;
    }

    public Ruler getSovereign() {
        return sovereign;
    }

    private void setSovereign(Ruler sovereign) {
        this.sovereign = sovereign;
        sovereign.addContract(this);
    }

    public Governor getVassal() {
        return vassal;
    }

    private void setVassal(Governor vassal) {
        this.vassal = vassal;
        vassal.addContract(this);
    }

    public void remove(){
        if(this.vassal != null){
            Governor tmpG = this.vassal;
            this.vassal = null;
            tmpG.removeContract(this);
        }
        if(this.sovereign != null){
            Ruler tmpR = this.sovereign;
            this.sovereign = null;
            tmpR.removeContract(this);
        }
        extent.remove(this);
    }

    public static Set<GoverningContract> getGoverningContracts() {
        return Collections.unmodifiableSet(extent);
    }

}
