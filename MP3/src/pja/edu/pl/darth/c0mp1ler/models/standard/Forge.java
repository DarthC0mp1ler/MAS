package pja.edu.pl.darth.c0mp1ler.models.standard;

import pja.edu.pl.darth.c0mp1ler.exceptions.NullValidationException;

import java.util.*;

public class Forge extends Building{

    private String smith;
    private final Queue<String> thingsToRepair  = new LinkedList<>();
    private final List<String> repairedThings = new ArrayList<>();

    public Forge(String name, String owner, String smith) {
        super(name, owner);
        setSmith(smith);
    }

    public Forge(String name, String smith) {
        super(name);
        setSmith(smith);
    }

    public String getSmith() {
        return smith;
    }

    public void setSmith(String smith) {
        if (smith == null || smith.isBlank()) {
            throw new NullValidationException("Smith cannot be empty");
        }
        this.smith = smith;
    }

    public void addThingsToRepair(String thing){
        if(thing == null || thing.isBlank()) {
            throw new NullValidationException("Resource cannot be empty");
        }
        thingsToRepair.add(thing);
    }

    public void removeThingsToRepair(String thing){
        if(thing == null || thing.isBlank()) {
            throw new NullValidationException("Resource cannot be empty");
        }
        thingsToRepair.remove(thing);
    }

    public List<String> getThingsToRepair() {
        return new ArrayList<>(thingsToRepair);
    }

    public List<String> getRepairedThings() {
        return Collections.unmodifiableList(repairedThings);
    }

    @Override
    public String retrieve(String thing){
        if(thing == null || thing.isBlank()) {
            throw new NullValidationException("Resource cannot be empty");
        }
        if(repairedThings.contains(thing)){
            repairedThings.remove(thing);
            return thing;
        }
        return null;
    }

    @Override
    public void operate() {
        repairedThings.add(thingsToRepair.poll());
    }

}
