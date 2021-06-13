package pja.edu.pl.darth.c0mp1ler.models;

import pja.edu.pl.darth.c0mp1ler.exceptions.NullValidationException;

public class Landlord {

    private String name;
    private String fathersName;

    private Governor manager;

    public Landlord(String name, String fathersName) {
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

    public Governor getManager() {
        return manager;
    }

    public void setManager(Governor manager) {
        if(this.manager == manager){
            return;
        }
        if (this.manager != null) {
            this.manager.removeSubordinate(this);
        }
        this.manager = manager;
        if (this.manager != null) {
            this.manager.addSubordinate(this);
        }
    }
}
