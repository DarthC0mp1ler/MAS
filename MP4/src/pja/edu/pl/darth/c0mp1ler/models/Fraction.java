package pja.edu.pl.darth.c0mp1ler.models;

import pja.edu.pl.darth.c0mp1ler.exceptions.NullValidationException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fraction {

    private String reason;

    private final List<Landlord> rebels = new ArrayList<>();

    public Fraction(String reason,Landlord leader) {
        setReason(reason);
        addRebel(leader);
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        if (reason == null || reason.isBlank())  {
            throw new NullValidationException("Reason cannot be empty");
        }
        this.reason = reason;
    }

    public List<Landlord> getRebels() {
        return Collections.unmodifiableList(rebels);
    }

    public Landlord getLeader(){
        return rebels.get(0);
    }

    public void makeLeader(Landlord landlord){
        if(landlord == null){
            throw new NullValidationException("Rebel cannot be empty");
        }
        if(rebels.contains(landlord)){
            rebels.remove(landlord);
            rebels.add(0,landlord);
        }
    }

    public void addRebel(Landlord landlord){
        if(landlord == null){
            throw new NullValidationException("Rebel cannot be empty");
        }
        if(!rebels.contains(landlord)){
            rebels.add(landlord);
        }
    }

    public void removeRebel(Landlord landlord) {
        if(landlord == null){
            throw new NullValidationException("Rebel cannot be empty");
        }
        if(rebels.size() <= 1){
            throw new NullValidationException("There must be at least one rebel");
        }
        rebels.remove(landlord);
    }
}
