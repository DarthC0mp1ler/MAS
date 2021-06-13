package pja.edu.pl.darth.c0mp1ler.models;

import pja.edu.pl.darth.c0mp1ler.exceptions.ContentViolationException;
import pja.edu.pl.darth.c0mp1ler.exceptions.NullValidationException;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Governor {
    private String name;
    private String fathersName;
    private final Set<Region> regions = new HashSet<>();

    private final Set<GoverningContract> governingContracts = new HashSet<>();

    public Governor(String name, String fathersName) {
        setName(name);
        setFathersName(fathersName);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new NullValidationException("Governor's name cannot be empty");
        }
        this.name = name;
    }

    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        if (fathersName == null || fathersName.isBlank()) {
            throw new NullValidationException("Governor father's name cannot be empty");
        }
        this.fathersName = fathersName;
    }

    public Set<Region> getRegions() {
        return Collections.unmodifiableSet(regions);
    }

    public void addRegion(Region region) {
        if (region == null) {
            throw new NullValidationException("Region set cannot be empty");
        }
        if(region.getGovernor() == this && regions.contains(region)) {
            return;
        }
        regions.add(region);
        region.setGovernor(this);
    }

    public void removeRegion(Region region) {
        if (region == null) {
            throw new NullValidationException("Region set cannot be empty");
        }
        if(region.getGovernor() != this) {
            regions.remove(region);
        }
    }

    public Set<GoverningContract> getGoverningContracts() {
        return Collections.unmodifiableSet(governingContracts);
    }

    public void addGoverningContract(GoverningContract contract) {
        if(contract == null) {
            throw new NullValidationException("Gouverning contract cannot be null");
        }
        if(contract.getGovernor() != this) {
            throw new ContentViolationException("This governor is not an owner of the provided contract");
        }
        this.governingContracts.add(contract);
    }

    public void removeGoverningContract(GoverningContract contract) {
        if(contract == null) {
            throw new NullValidationException("Gouverning contract cannot be null");
        }
        this.governingContracts.remove(contract);
        if(contract.getGovernor() == this){
            contract.remove();
        }
    }
}
