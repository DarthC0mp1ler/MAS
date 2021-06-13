package pja.edu.pl.darth.c0mp1ler.models;

import pja.edu.pl.darth.c0mp1ler.exceptions.ContentViolationException;
import pja.edu.pl.darth.c0mp1ler.exceptions.NullValidationException;
import pja.edu.pl.darth.c0mp1ler.typeEnums.ConstructionType;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Construction {

    private String name;
    private ConstructionType constructionType;
    private LocalDate constructionDate;

    private Landlord manager;

    private Region region;

    private final Set<Building> buildings = new HashSet<>();

    public Construction(String name, ConstructionType constructionType, LocalDate constructionDate, Landlord manager) {
        this.name = name;
        this.constructionType = constructionType;
        this.constructionDate = constructionDate;
    }

    public Construction(String name, ConstructionType constructionType, LocalDate constructionDate) {
        this.name = name;
        this.constructionType = constructionType;
        this.constructionDate = constructionDate;
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

    public ConstructionType getConstructionType() {
        return constructionType;
    }

    public void setConstructionType(ConstructionType constructionType) {
        if (constructionType == null) {
            throw new NullValidationException("Construction type cannot be empty");
        }
        this.constructionType = constructionType;
    }

    public LocalDate getConstructionDate() {
        return constructionDate;
    }

    public void setConstructionDate(LocalDate constructionDate) {
        if (constructionDate == null) {
            throw new NullValidationException("Construction date cannot be null");
        }
        this.constructionDate = constructionDate;
    }

    public Landlord getManager() {
        return manager;
    }

    public void setManager(Landlord manager) {
        if(this.manager == manager){
            return;
        }
        this.manager = manager;
        if(manager != null){
            this.manager.setConstruction(this);
        }
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        if(this.region == region) {
            return;
        }
        Region tmp = this.region;
        if(tmp != null){
            tmp.removeBelonging(this);
        }
        this.region = region;
        if(region != null){
            region.addBelonging(this);
        }
    }

    public Set<Building> getBuildings() {
        return Collections.unmodifiableSet(buildings);
    }

    public void addBuilding(Building building){
        if(building == null) {
            throw new NullValidationException("Building cannot be null");
        }
        if(building.getConstruction() != this){
            throw new ContentViolationException("Building does not belong to this construction");
        }
        buildings.add(building);
    }

    public void removeBuilding(Building building) {
        if(building == null) {
            throw new NullValidationException("Building cannot be null");
        }
        if(buildings.contains(building)){
            buildings.remove(building);
            building.delete();
        }
    }

    public void delete(){
        for (Building b:new HashSet<>(buildings)) {
            b.delete();
        }
        setManager(null);
        setRegion(null);
    }
}
