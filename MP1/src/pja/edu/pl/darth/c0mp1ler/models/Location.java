package pja.edu.pl.darth.c0mp1ler.models;

import pja.edu.pl.darth.c0mp1ler.exceptions.ContentViolationException;
import pja.edu.pl.darth.c0mp1ler.exceptions.NullValidationException;

import java.io.Serializable;
import java.util.*;

public class Location implements Serializable {
    private String kingdom;
    private String region;
    private String holding;
    private String ownerName;
    private final Set<String> resources = new HashSet<>();

    public Location(String kingdom, String region, String holding, String resources) {
        setKingdom(kingdom);
        setRegion(region);
        setHolding(holding);
        addResource(resources);
    }

    public Location(String kingdom, String region, String holding, String resources, String ownerName) {
        setKingdom(kingdom);
        setRegion(region);
        setHolding(holding);
        setOwnerName(ownerName);
        addResource(resources);
    }

    public String getKingdom() {
        return kingdom;
    }

    public void setKingdom(String kingdom) {
        if (kingdom == null || kingdom.isBlank()) {
            throw new NullValidationException("Kingdom name cannot be empty");
        }
        this.kingdom = kingdom;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        if (region == null || region.isBlank()) {
            throw new NullValidationException("Duchy name cannot be empty");
        }
        this.region = region;
    }

    public String getHolding() {
        return holding;
    }

    public void setHolding(String holding) {
        if (holding == null || holding.isBlank()) {
            throw new NullValidationException("County name cannot be empty");
        }
        this.holding = holding;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        if(ownerName != null && ownerName.isBlank()){
            this.ownerName = null;
        }
        this.ownerName = ownerName;
    }

    public Set<String> getResources() {
        return Collections.unmodifiableSet(resources);
    }

    public void addResource(String res) {
        if (res == null || res.isBlank()) {
            throw new NullValidationException("Resource cannot be null");
        }
        this.resources.add(res);
    }

    public void removeResources(String res) {

        if (res == null || res.isBlank()) {
            throw new NullValidationException("Resource cannot be null");
        }
        if (this.resources.size() > 1)
        {
            this.resources.remove(res);
        }
        else {
            throw new ContentViolationException("Resources should have at least 1 element");
        }
    }

    @Override
    public String toString()
    {
        return holding + " of "  + region + " of " + kingdom + ((ownerName == null || ownerName.isBlank())?" belonging to " + ownerName : "");
    }

}
