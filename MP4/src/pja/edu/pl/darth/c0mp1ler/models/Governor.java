package pja.edu.pl.darth.c0mp1ler.models;

import pja.edu.pl.darth.c0mp1ler.exceptions.ContentViolationException;
import pja.edu.pl.darth.c0mp1ler.exceptions.NullValidationException;

import java.util.*;

public class Governor {

    private String name;
    private String fathersName;

    private final Set<GoverningContract> contracts = new HashSet<>();
    private final List<Landlord> subordinates = new ArrayList<>();

    private Region region;
    private Kingdom kingdom;

    public Governor(String name, String fathersName) {
        setName(name);
        setFathersName(fathersName);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new NullValidationException("Name cannot be empty");
        }
        this.name = name;
    }

    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        if (fathersName == null || fathersName.isBlank()) {
            throw new NullValidationException("Fathers name cannot be empty");
        }
        this.fathersName = fathersName;
    }

    public Set<GoverningContract> getContracts() {
        return Collections.unmodifiableSet(contracts);
    }

    public void addContract(GoverningContract contract){
        if(contract == null) {
            throw new NullValidationException("Governing contract cannot be null");
        }
        if(contract.getVassal() != this) {
            throw new ContentViolationException("This governor is not an owner of the provided contract");
        }
        this.contracts.add(contract);
    }

    public void removeContract(GoverningContract contract){
        if(contract == null) {
            throw new NullValidationException("Governing contract cannot be null");
        }
        this.contracts.remove(contract);
        if(contract.getVassal() == this){
            contract.remove();
        }
    }

    public List<Landlord> getSubordinates(){
        return Collections.unmodifiableList(subordinates);
    }

    public void addSubordinate(Landlord landlord){
        if(landlord == null){
            throw new NullValidationException("Subordinate cannot be null");
        }
        if(subordinates.contains(landlord)){
            throw new ContentViolationException("Governor already manages this landlord");
        }
        subordinates.add(landlord);
        landlord.setManager(this);
    }

    public void removeSubordinate(Landlord landlord){
        if(landlord == null){
            throw new NullValidationException("Subordinate cannot be null");
        }
        subordinates.remove(landlord);
        landlord.setManager(null);
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        if(this.region == region){
            return;
        }
        if(region != null) {
            setKingdom(null);
        }
        Region tmp = this.region;
        this.region = region;
        if(tmp != null){
            tmp.setGovernor(null);
        }
        if(this.region != null){
            this.region.setGovernor(this);
        }
    }

    public Kingdom getKingdom() {
        return kingdom;
    }

    public void setKingdom(Kingdom kingdom) {
        if(this.kingdom == kingdom){
            return;
        }
        if(kingdom != null) {
            setRegion(null);
        }
        Kingdom tmp = this.kingdom;
        this.kingdom = kingdom;
        if(tmp != null){
            tmp.setAdvisor(null);
        }
        if(this.kingdom != null){
            this.kingdom.setAdvisor(this);
        }
    }
}
