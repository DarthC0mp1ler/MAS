package pja.edu.pl.darth.c0mp1ler.models.dynamic;

import pja.edu.pl.darth.c0mp1ler.exceptions.ContentViolationException;
import pja.edu.pl.darth.c0mp1ler.exceptions.NullValidationException;

public class Person implements Governor, Landlord {

    private String name;
    private String fathersName;

    private Long governingRegionID;
    private String managingConstruction;

    private PersonType personType;

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
        throw new ContentViolationException("This object does not have the property requested");
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
        throw new ContentViolationException("This object does not have the property requested");
    }

    @Override
    public Long getGoverningRegionID() {
        if(isGovernor()) {
            return governingRegionID;
        }
        throw new ContentViolationException("This object does not have the property requested");
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
        throw new ContentViolationException("This object does not have the property requested");
    }

    private void setPersonType(PersonType personType){
        if(personType == null){
            throw new NullValidationException("Person type cannot be empty");
        }
        this.personType = personType;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public boolean isGovernor(){
        return personType.equals(PersonType.Governor);
    }

    public boolean isLandlord(){
        return personType.equals(PersonType.Landlord);
    }

    public void becomeGovernor(Long governingRegionID){
        String tmp = managingConstruction;
        if(isLandlord()){
            try {
                managingConstruction = null;
                setPersonType(PersonType.Governor);
                setGoverningRegionID(governingRegionID);
                return;
            }catch (NullValidationException | ContentViolationException e){
                setPersonType(PersonType.Landlord);
                setManagingConstruction(tmp);
            }
        }
        throw new ContentViolationException("This object does not have requested functionality");
    }

    public void becomeLandlord(String managingConstruction){
        Long tmp = governingRegionID;
        if(isGovernor()){
            try {
                governingRegionID = null;
                setPersonType(PersonType.Landlord);
                setManagingConstruction(managingConstruction);
                return;
            }catch (NullValidationException | ContentViolationException e){
                setPersonType(PersonType.Governor);
                setGoverningRegionID(governingRegionID);
            }
        }
        throw new ContentViolationException("This object does not have requested functionality");
    }
}
