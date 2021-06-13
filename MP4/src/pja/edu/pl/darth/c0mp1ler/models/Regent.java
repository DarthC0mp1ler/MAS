package pja.edu.pl.darth.c0mp1ler.models;

import pja.edu.pl.darth.c0mp1ler.exceptions.ContentViolationException;
import pja.edu.pl.darth.c0mp1ler.exceptions.NullValidationException;

public class Regent {

    private String name;
    private Kingdom kingdom;

    public Regent(String name) {
        setName(name);
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

    public Kingdom getKingdom() {
        return kingdom;
    }

    public void setKingdom(Kingdom kingdom) {
        if (this.kingdom == kingdom) {
            return;
        }
        if (this.kingdom != null) {
            if (this.kingdom.getRegent() != this) {
                if (kingdom != null) {
                    if (!kingdom.isRulerUnderAge()) {
                        throw new ContentViolationException("Ruler must be under age to assign a Regent");
                    }
                    if(kingdom.getRegent() == this ){
                        this.kingdom = kingdom;
                    }
                }
            }
        } else {
            if (!kingdom.isRulerUnderAge()) {
                throw new ContentViolationException("Ruler must be under age to assign a Regent");
            }
            if(kingdom.getRegent() == this ){
                this.kingdom = kingdom;
            }
        }
    }
}
