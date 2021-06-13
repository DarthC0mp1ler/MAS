package pja.edu.pl.darth.c0mp1ler.models;

import pja.edu.pl.darth.c0mp1ler.exceptions.NullValidationException;
import pja.edu.pl.darth.c0mp1ler.typeEnums.GoverningContractType;

import java.time.LocalDate;

public class GoverningContract {

    private GoverningContractType type;
    private LocalDate signDate;
    private LocalDate terminationDate;
    private float tax;

    private Governor governor;
    private Landlord landlord;

    public GoverningContract( LocalDate signDate, float tax, Governor governor, Landlord landlord) {
       setLongTermType();
       setSignDate(signDate);
       setTax(tax);
       if(landlord != null && governor != null) {
           setLandlord(landlord);
           setGovernor(governor);
       }else {
           throw new NullValidationException("empty parameters are unexpected");
       }
    }

    public GoverningContract( LocalDate signDate,LocalDate terminationDate, float tax, Governor governor, Landlord landlord) {
        setFixedTermType(terminationDate);
        setSignDate(signDate);
        setTax(tax);
        if(landlord != null && governor != null) {
            setLandlord(landlord);
            setGovernor(governor);
        }else {
            throw new NullValidationException("empty parameters are unexpected");
        }
    }

    public GoverningContractType getType() {
        return type;
    }

    public void setLongTermType() {
        this.type = GoverningContractType.LONG_TERM;
    }
    public void setFixedTermType(LocalDate terminationDate) {
        setTerminationDate(terminationDate);
        this.type = GoverningContractType.FIXED_TERM;
    }

    public LocalDate getSignDate() {
        return signDate;
    }

    public void setSignDate(LocalDate signDate) {
        this.signDate = signDate;
    }

    public LocalDate getTerminationDate() {
        return terminationDate;
    }

    private void setTerminationDate(LocalDate terminationDate) {
        this.terminationDate = terminationDate;
    }

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public Governor getGovernor() {
        return governor;
    }

    private void setGovernor(Governor governor) {
        if (governor == null) {
            throw new NullValidationException("Governor cannot be empty");
        }
        this.governor = governor;
        governor.addGoverningContract(this);
    }

    public Landlord getLandlord() {
        return landlord;
    }

    private void setLandlord(Landlord landlord) {
        if (landlord == null) {
            throw new NullValidationException("Landlord cannot be null");
        }
        if(landlord.getContract() != null) {
            landlord.getContract().remove();
        }
        this.landlord = landlord;
        landlord.setContract(this);
    }

    public void remove(){
        if(this.landlord != null){
            Landlord tmpL = this.landlord;
            this.landlord = null;
            tmpL.setContract(null);
        }
        if(this.governor != null){
            Governor tmpG = this.governor;
            this.governor = null;
            tmpG.removeGoverningContract(this);

        }
    }
}
