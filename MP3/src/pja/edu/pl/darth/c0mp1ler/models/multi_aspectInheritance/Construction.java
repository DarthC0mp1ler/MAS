package pja.edu.pl.darth.c0mp1ler.models.multi_aspectInheritance;

import pja.edu.pl.darth.c0mp1ler.exceptions.NullValidationException;

import java.time.LocalDate;

public abstract class Construction {
    private String name;
    private LocalDate constructionDate;

    private DevelopmentDirection direction;

    public Construction(String name,  LocalDate constructionDate, DevelopmentDirection direction) {
        setName(name);
        setConstructionDate(constructionDate);
        setDirection(direction);
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

    public LocalDate getConstructionDate() {
        return constructionDate;
    }

    public void setConstructionDate(LocalDate constructionDate) {
        if (constructionDate == null) {
            throw new NullValidationException("Construction date cannot be empty");
        }
        this.constructionDate = constructionDate;
    }

    public DevelopmentDirection getDirection() {
        return direction;
    }

    public void setDirection(DevelopmentDirection direction) {
        if (direction == null) {
            throw new NullValidationException("Development direction cannot be empty");
        }
        if(this.direction == direction){
            return;
        }
        DevelopmentDirection tmp = this.direction;

        this.direction = direction;
        direction.setConstruction(this);
        if(tmp != null){
            tmp.setConstruction(null);
        }
    }


}
