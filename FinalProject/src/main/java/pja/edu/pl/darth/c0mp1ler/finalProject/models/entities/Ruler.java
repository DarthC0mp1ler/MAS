package pja.edu.pl.darth.c0mp1ler.finalProject.models.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pja.edu.pl.darth.c0mp1ler.finalProject.exceptions.ContentViolationException;
import pja.edu.pl.darth.c0mp1ler.finalProject.exceptions.NullValidationException;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity representing Ruler
 * @see Person
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Ruler extends Person {

    @NotNull
    private LocalDate startRuling;

    @OneToMany(mappedBy = "ruler", cascade = CascadeType.REMOVE)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private final Set<GoverningContract> contracts = new HashSet<>();

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "frationAgainst_id")
    private Fraction fractionAgainst;

    @OneToOne(mappedBy = "ruler")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Capital livingPlace;

    @OneToOne(mappedBy = "ruler")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Kingdom kingdom;

    /**
     * Derived attribute
     * @return ruling time as Period
     */
    @Transient
    public Period getRulingTime(){
        return Period.between(startRuling,LocalDate.now());
    }

}
