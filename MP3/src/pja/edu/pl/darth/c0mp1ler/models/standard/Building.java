package pja.edu.pl.darth.c0mp1ler.models.standard;

import pja.edu.pl.darth.c0mp1ler.exceptions.NullValidationException;

public abstract class Building {
    protected String name;
    protected String owner;

    public Building(String name, String owner) {
        setName(name);
        setOwner(owner);
    }

    public Building(String name) {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new NullValidationException("Building name cannot be null");
        }
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        if (owner.isBlank()) {
            throw new NullValidationException("Owner cannot be empty");
        }
        this.owner = owner;
    }

    public abstract void operate();
    public abstract String retrieve(String res);
}
