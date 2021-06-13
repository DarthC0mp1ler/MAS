package pja.edu.pl.darth.c0mp1ler.models.multiInheritance;

import pja.edu.pl.darth.c0mp1ler.exceptions.ContentViolationException;
import pja.edu.pl.darth.c0mp1ler.exceptions.NullValidationException;

import java.time.LocalDate;

public class Capital extends Construction implements Region{

    private String name;
    private float area;

    public Capital(String name, ConstructionType type, LocalDate constructionDate, String regionName, float area) {
        super(name, type, constructionDate);
        setName(regionName);
        setArea(area);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new NullValidationException("name cannot be empty");
        }
        this.name = name;
    }

    @Override
    public float getArea() {
        return area;
    }

    @Override
    public void setArea(float area) {
        if (area <= 0) {
            throw new ContentViolationException("Area cannot be negative");
        }
        this.area = area;
    }
}
