package pja.edu.pl.darth.c0mp1ler.models;

import pja.edu.pl.darth.c0mp1ler.exceptions.ContentViolationException;
import pja.edu.pl.darth.c0mp1ler.exceptions.NullValidationException;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Ruler {

    private String name;
    private String fathersName;
    private LocalDate birthDate;
    private Kingdom kingdom;

    private final Set<GoverningContract> contracts = new HashSet<>();

    public Ruler(String name, String fathersName, LocalDate birthDate) {
        setName(name);
        setFathersName(fathersName);
        setBirthDate(birthDate);
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    private void setBirthDate(LocalDate birthDate) {
        if (birthDate == null) {
            throw new NullValidationException("Birthdate cannot be empty");
        }
        this.birthDate = birthDate;
    }

    public Period getAge(){
        return Period.between(birthDate,LocalDate.now());
    }

    public Set<GoverningContract> getContracts() {
        return Collections.unmodifiableSet(contracts);
    }

    public void addContract(GoverningContract contract){
        if(contract == null) {
            throw new NullValidationException("Governing contract cannot be null");
        }
        if(contract.getSovereign() != this) {
            throw new ContentViolationException("This governor is not an owner of the provided contract");
        }
        this.contracts.add(contract);
    }

    public void removeContract(GoverningContract contract){
        if(contract == null) {
            throw new NullValidationException("Governing contract cannot be null");
        }
        this.contracts.remove(contract);
        if(contract.getSovereign() == this){
            contract.remove();
        }
    }

    public Kingdom getKingdom() {
        return kingdom;
    }

    public void setKingdom(Kingdom kingdom) {
        if(this.kingdom == kingdom){
            return;
        }
        if(this.kingdom != null && this.kingdom.getRuler() != this && kingdom == null){
            throw new ContentViolationException("Kingdom must have a Ruler");
        }
        this.kingdom = kingdom;
    }
}
