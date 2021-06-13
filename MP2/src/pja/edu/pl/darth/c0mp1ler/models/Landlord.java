package pja.edu.pl.darth.c0mp1ler.models;

import pja.edu.pl.darth.c0mp1ler.exceptions.ContentViolationException;
import pja.edu.pl.darth.c0mp1ler.exceptions.NullValidationException;

public class Landlord {

    private String name;
    private String fathersName;
    private GoverningContract contract;

    private Construction construction;

    public Landlord(String name, String fathersName, Construction construction) {
        setName(name);
        setFathersName(fathersName);
        setConstruction(construction);
    }

    public Landlord(String name, String fathersName) {
        setName(name);
        setFathersName(fathersName);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new NullValidationException("Landlord's name cannot be empty");
        }
        this.name = name;
    }

    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        if (fathersName == null || fathersName.isBlank()) {
            throw new NullValidationException("Landlord father's name cannot be empty");
        }
        this.fathersName = fathersName;
    }

    public Construction getConstruction() {
        return construction;
    }

    public void setConstruction(Construction construction) {
        if (this.construction == construction) {
            return;
        }
        if (this.construction != null) {
            this.construction.setManager(null);
        }
        this.construction = construction;
        if (this.construction != null) {
            this.construction.setManager(this);
        }
    }

    public GoverningContract getContract() {
        return contract;
    }

    public void setContract(GoverningContract contract) {
        if (contract == null) {
            if (this.contract != null && this.contract.getLandlord() == null) {
                this.contract = null;
            }
            if (this.contract != null) {
                this.contract.remove();
            }
        } else {
            if (contract.getLandlord() != this) {
                throw new ContentViolationException("This landlord is not an owner of the provided contract");
            }
            this.contract = contract;
        }
    }
}
