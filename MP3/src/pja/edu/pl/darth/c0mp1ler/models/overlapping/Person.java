package pja.edu.pl.darth.c0mp1ler.models.overlapping;

import pja.edu.pl.darth.c0mp1ler.exceptions.ContentViolationException;
import pja.edu.pl.darth.c0mp1ler.exceptions.NullValidationException;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class Person implements Landlord, Governor {

    private String name;
    private String fathersName;

    private Long governingRegionID;
    private String managingConstruction;

    private EnumSet<PersonType> personTypes;

    public Person(String name, String fathersName, Long governingRegionID, String managingConstruction) {
        setPersonType(PersonType.Governor,PersonType.Landlord);
        setName(name);
        setFathersName(fathersName);
        setGoverningRegionID(governingRegionID);
        setManagingConstruction(managingConstruction);
    }

    public Person(String name, String fathersName, Long governingRegionID) {
        setPersonType(PersonType.Governor);
        setName(name);
        setFathersName(fathersName);
        setGoverningRegionID(governingRegionID);
    }

    public Person(String name, String fathersName, String managingConstruction) {
        setPersonType(PersonType.Landlord);
        setName(name);
        setFathersName(fathersName);
        setManagingConstruction(managingConstruction);
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

    @Override
    public String getManagingConstruction() {
        if(isLandlord()) {
            return managingConstruction;
        }
        throw new ContentViolationException("This object does not have property requested");
    }

    @Override
    public void setManagingConstruction(String managingConstruction) {
        if(isLandlord()) {
            if (managingConstruction == null || managingConstruction.isBlank()) {
                throw new NullValidationException("Construction cannot be empty");
            }
            this.managingConstruction = managingConstruction;
            return;
        }
        throw new ContentViolationException("This object does not have property requested");
    }

    @Override
    public Long getGoverningRegionID() {
        if(isGovernor()) {
            return governingRegionID;
        }
        throw new ContentViolationException("This object does not have property requested");
    }

    @Override
    public void setGoverningRegionID(Long governingRegionID) {
        if(isGovernor()) {
            if (governingRegionID == null) {
                throw new NullValidationException("region cannot be empty");
            }
            this.governingRegionID = governingRegionID;
            return;
        }
        throw new ContentViolationException("This object does not have property requested");
    }

    public boolean isGovernor(){
        return personTypes.contains(PersonType.Governor);
    }

    public boolean isLandlord(){
        return personTypes.contains(PersonType.Landlord);
    }

    private void setPersonType(PersonType... personType){
        if(personType == null || personType.length == 0){
            throw new NullValidationException("Person type cannot be empty");
        }
        if(personTypes == null) {
            personTypes = EnumSet.copyOf(Arrays.asList(personType));
        }
    }

    public Set<PersonType> getPersonTypes() {
        return new HashSet<>(personTypes);
    }
}
