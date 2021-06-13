package pja.edu.pl.darth.c0mp1ler.models.multiInheritance;

import pja.edu.pl.darth.c0mp1ler.exceptions.NullValidationException;

import java.time.LocalDate;

public class Construction {

    private String name;
    private ConstructionType type;
    private LocalDate constructionDate;

    public Construction(String name, ConstructionType type, LocalDate constructionDate) {
        setName(name);
        setType(type);
        setConstructionDate(constructionDate);
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

    public ConstructionType getType() {
        return type;
    }

    public void setType(ConstructionType type) {
        if (type == null) {
            throw new NullValidationException("Construction type cannot be empty");
        }
        this.type = type;
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
}
