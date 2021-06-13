package pja.edu.pl.darth.c0mp1ler.models;

import pja.edu.pl.darth.c0mp1ler.exceptions.NullValidationException;
import pja.edu.pl.darth.c0mp1ler.typeEnums.BuildingType;

public class Building {

    private String name;
    private BuildingType type;

    private Construction construction;

    private Building(String name, BuildingType type, Construction construction) {
        setName(name);
        setType(type);
        setConstruction(construction);
    }

    public static Building createBuilding(String name, BuildingType type, Construction construction){
        Building building = new Building(name,type,construction);
        construction.addBuilding(building);
        return building;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new NullValidationException("Building name cannot be empty");
        }
        this.name = name;
    }

    public BuildingType getType() {
        return type;
    }

    public void setType(BuildingType type) {
        if (type == null) {
            throw new NullValidationException("Building type cannot be null");
        }
        this.type = type;
    }

    public Construction getConstruction() {
        return construction;
    }

    private void setConstruction(Construction construction) {
        if (construction == null) {
            throw new NullValidationException("Construction cannot be null");
        }
        this.construction = construction;
    }

    public void delete(){
        if(this.construction == null) {
            return;
        }
        Construction tmp = this.construction;
        this.construction = null;
        tmp.removeBuilding(this);
    }
}
