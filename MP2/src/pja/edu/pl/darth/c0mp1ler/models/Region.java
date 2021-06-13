package pja.edu.pl.darth.c0mp1ler.models;

import pja.edu.pl.darth.c0mp1ler.exceptions.ContentViolationException;
import pja.edu.pl.darth.c0mp1ler.exceptions.NullValidationException;

import java.util.*;

public class Region {

    private String name;
    private float area;
    private Governor governor;

    private final Map<String,Construction> belongings = new HashMap<>();

    public Region(String name, float area,Governor governor) {
        setName(name);
        setArea(area);
        setGovernor(governor);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new NullValidationException("Region name cannot be empty");
        }
        this.name = name;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        if (area < 0) {
            throw new ContentViolationException("Region's area cannot be negative");
        }
        this.area = area;
    }

    public Governor getGovernor() {
        return governor;
    }

    public void setGovernor(Governor governor) {
        if(governor == null) {
            throw new NullValidationException("Governor set cannot be null");
        }
        if(this.governor == governor) {
            return;
        }
        Governor previousGovernor = this.governor;
        this.governor = governor;
        if(previousGovernor != null) {
            previousGovernor.removeRegion(this);
        }
        this.governor.addRegion(this);
    }

    public Map<String, Construction> getBelongings() {
        return Collections.unmodifiableMap(belongings);
    }

    public List<Construction> getBelongingsList(){
        return new ArrayList<>(belongings.values());
    }

    public void addBelonging(Construction construction){
        if(construction == null){
            throw new NullValidationException("Construction cannot be empty");
        }
        if(belongings.containsKey(construction.getName())){
            if(belongings.get(construction.getName()) != construction) {
                throw new ContentViolationException("Construction with the same name already exists");
            }
            return;
        }
        belongings.put(construction.getName(),construction);
        construction.setRegion(this);
    }

    public void removeBelonging(Construction construction){
        if(construction == null){
            throw new NullValidationException("Construction cannot be empty");
        }
        if(belongings.remove(construction.getName(),construction)){
            construction.setRegion(null);
        }
    }
}
