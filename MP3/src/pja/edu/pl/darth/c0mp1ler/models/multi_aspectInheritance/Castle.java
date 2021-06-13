package pja.edu.pl.darth.c0mp1ler.models.multi_aspectInheritance;

import pja.edu.pl.darth.c0mp1ler.exceptions.NullValidationException;

import java.time.LocalDate;

public class Castle extends Construction{

    private String FamilyName;

    public Castle(String name, LocalDate constructionDate, DevelopmentDirection direction, String familyName) {
        super(name, constructionDate, direction);
        setFamilyName(familyName);
    }

    public String getFamilyName() {
        return FamilyName;
    }

    public void setFamilyName(String familyName) {
        if (familyName == null) {
            throw new NullValidationException("Family name cannot be null");
        }
        FamilyName = familyName;
    }
}
