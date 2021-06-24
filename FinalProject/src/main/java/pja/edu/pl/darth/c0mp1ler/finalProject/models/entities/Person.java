package pja.edu.pl.darth.c0mp1ler.finalProject.models.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pja.edu.pl.darth.c0mp1ler.finalProject.exceptions.ContentViolationException;
import pja.edu.pl.darth.c0mp1ler.finalProject.exceptions.NullValidationException;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.enums.MagicType;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.enums.Race;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.enums.Sex;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity representing a Person
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String familyName;
    @NotBlank
    private String fathersName;

    private float wealth;
    @NotNull
    private Sex sex;
    @NotNull
    private Race race;

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private MagicType magicType;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "HumanDiseases", joinColumns = @JoinColumn(name = "human_id"))
    @Builder.Default
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private final Set<String> diseases = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "fraction_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Fraction fraction;

    @OneToOne
    @JoinColumn(name = "frationLeader_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Fraction leaderInFraction;

    @NotNull
    private LocalDate birthDate;

    public Period getAge(){
        return Period.between(birthDate,LocalDate.now());
    }

    /**
     * getter for magic type
     * if race is not elf the following property is not accessible
     * @throws ContentViolationException if an elf is
     * @return MagicType
     * @see MagicType
     */
    public MagicType getMagicType() {
        if (race.equals(Race.Elf)) {
            return magicType;
        }
        throw new ContentViolationException("This object does not have the requested attribute");
    }

    /**
     * setter for magic type
     * if race is not elf the following property is not accessible
     * @throws ContentViolationException if an elf is
     * @param magicType Magic type to be set
     * @see MagicType
     */
    public void setMagicType(MagicType magicType) {
        if(race.equals(Race.Elf)){
            if(magicType == null){
                throw new NullValidationException("Magic type cannot be null");
            }
            this.magicType = magicType;
            return;
        }
        throw new ContentViolationException("This object does not have the requested attribute");
    }

    /**
     * getter for Diseases
     * if race is not human the following property is not accessible
     * @return list of diseases
     */
    public Set<String> getDiseases() {
        if(race.equals(Race.Human)) {
            return diseases;
        }
        throw new ContentViolationException("This object does not have the requested attribute");
    }

    /**
     * Create person use case
     * Creates person
     */
    public static void createPerson(){
        // FIXME: 6/18/2021 Unimplemented use case
    }
}
