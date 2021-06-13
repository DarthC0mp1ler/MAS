package pja.edu.pl.darth.c0mp1ler.models;

import pja.edu.pl.darth.c0mp1ler.exceptions.ContentViolationException;
import pja.edu.pl.darth.c0mp1ler.exceptions.NullValidationException;

public class Kingdom {

    private String name;
    private Ruler ruler;
    private Regent regent;
    private Governor advisor;

    public Kingdom(String name, Ruler ruler) {
        setName(name);
        setRuler(ruler);
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

    public Ruler getRuler() {
        return ruler;
    }

    public void setRuler(Ruler ruler) {
        if (ruler == null) {
            throw new NullValidationException("Ruler cannot be empty");
        }
        if(ruler == this.ruler){
            return;
        }
        if(this.ruler != null){
            this.ruler.setKingdom(null);
        }
        this.ruler = ruler;
        this.ruler.setKingdom(this);
        if(!isRulerUnderAge()){
            setRegent(null);
        }
    }

    public Regent getRegent() {
        return regent;
    }

    public void setRegent(Regent regent) {
        if(this.regent == regent){
            return;
        }
        if(isRulerUnderAge()){
            if(regent == null){
                throw new ContentViolationException("Kingdom with Ruler under age must have a regent");
            }
            if(this.regent != null){
                Regent tmp = this.regent;
                this.regent = regent;
                tmp.setKingdom(null);
            }else{
                this.regent = regent;
            }
            regent.setKingdom(this);
        }else{
            if(this.regent != null){
                Regent tmp = this.regent;
                this.regent = null;
                tmp.setKingdom(null);
            }else{
                this.regent = null;
            }
        }
    }

    public boolean isRulerUnderAge(){
        return ruler.getAge().getYears() < 18;
    }

    public Governor getAdvisor() {
        return advisor;
    }

    public void setAdvisor(Governor advisor) {
        if(this.advisor == advisor){
            return;
        }
        if(this.advisor != null){
            this.advisor.setKingdom(null);
        }
        this.advisor = advisor;
        if(this.advisor != null){
            this.advisor.setKingdom(this);
        }
    }
}
