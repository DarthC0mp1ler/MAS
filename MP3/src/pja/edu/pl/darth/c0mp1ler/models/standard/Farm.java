package pja.edu.pl.darth.c0mp1ler.models.standard;

import pja.edu.pl.darth.c0mp1ler.exceptions.NullValidationException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Farm extends Building{

    private String farmer;
    private final List<String> producedResources = new ArrayList<>();
    private final List<String> possibleResources = new ArrayList<>();

    public Farm(String name, String owner, String farmer) {
        super(name, owner);
        setFarmer(farmer);
    }

    public Farm(String name, String farmer) {
        super(name);
        setFarmer(farmer);
    }

    public String getFarmer() {
        return farmer;
    }

    public void setFarmer(String farmer) {
        if (farmer == null || farmer.isBlank()) {
            throw new NullValidationException("Farmer cannot be empty");
        }
        this.farmer = farmer;
    }

    public List<String> getPossibleResources() {
        return Collections.unmodifiableList(possibleResources);
    }

    public void addPossibleResources(String res){
        if(res == null || res.isBlank()) {
            throw new NullValidationException("Resource cannot be empty");
        }
        possibleResources.add(res);
    }

    public void removePossibleResources(String res){
        if(res == null || res.isBlank()) {
            throw new NullValidationException("Resource cannot be empty");
        }
        possibleResources.remove(res);
    }

    public List<String> getProducedResources() {
        return Collections.unmodifiableList(producedResources);
    }

    @Override
    public String retrieve(String res) {
        if(res == null || res.isBlank()) {
            throw new NullValidationException("Resource cannot be empty");
        }
        if(producedResources.contains(res)){
            producedResources.remove(res);
            return res;
        }
        return null;
    }

    @Override
    public void operate() {
        if(!possibleResources.isEmpty()) {
            Random rand = new Random();
            String res = possibleResources.get(rand.nextInt(possibleResources.size()));
            producedResources.add(res);
        }
    }
}
