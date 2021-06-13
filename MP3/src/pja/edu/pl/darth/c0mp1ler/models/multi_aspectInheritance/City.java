package pja.edu.pl.darth.c0mp1ler.models.multi_aspectInheritance;

import pja.edu.pl.darth.c0mp1ler.exceptions.ContentViolationException;

import java.time.LocalDate;

public class City extends Construction{

    private int population;

    public City(String name, LocalDate constructionDate, DevelopmentDirection direction, int population) {
        super(name, constructionDate, direction);
        setPopulation(population);
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        if(population < 0){
            throw new ContentViolationException("city population cannot be negative");
        }
        this.population = population;
    }
}
