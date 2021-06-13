package pja.edu.pl.darth.c0mp1ler.models;

import pja.edu.pl.darth.c0mp1ler.exceptions.ContentViolationException;
import pja.edu.pl.darth.c0mp1ler.exceptions.NullValidationException;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Region {

    private static final Set<Region> extent = new HashSet<>();
    private String name;

    private Governor governor;

    public Region(String name) {
        setName(name);
        extent.add(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new NullValidationException("Name cannot be empty");
        }
        if(!isNameUnique(name))
        {
            throw new ContentViolationException("Name should be unique");
        }
        this.name = name;
    }

    public static Set<Region> getAllRegions() {
        return Collections.unmodifiableSet(extent);
    }

    private static boolean isNameUnique(String name){
        return extent.stream().noneMatch(r -> r.getName().equals(name));
    }

    public Governor getGovernor() {
        return governor;
    }

    public void setGovernor(Governor governor) {
        if(this.governor == governor){
            return;
        }
        if(this.governor != null){
            this.governor.setRegion(null);
        }
        this.governor = governor;
        if(this.governor != null){
            this.governor.setRegion(this);
        }
    }
}
