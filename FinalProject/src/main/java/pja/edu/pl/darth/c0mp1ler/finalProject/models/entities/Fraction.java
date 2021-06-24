package pja.edu.pl.darth.c0mp1ler.finalProject.models.entities;

import lombok.*;
import pja.edu.pl.darth.c0mp1ler.finalProject.exceptions.ContentViolationException;
import pja.edu.pl.darth.c0mp1ler.finalProject.exceptions.NullValidationException;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity representing the Fraction that people can form against some ruler
 * @see Person
 * @see Ruler
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Fraction {

    private static final long maxSupport = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Fraction_reasons", joinColumns = @JoinColumn(name = "fraction_id"))
    @Builder.Default
    private final Set<String> reasons = new HashSet<>();

    @Max(maxSupport)
    private float support;

    @NotNull
    @OneToMany(mappedBy = "fraction")
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Size(min = 1)
    @Setter(AccessLevel.NONE)
    private final Set<Person> rebels = new HashSet<>();

    @NotNull
    @OneToOne(mappedBy = "leaderInFraction", optional = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Setter(AccessLevel.NONE)
    private Person leader;

    @NotNull
    @OneToOne(mappedBy = "fractionAgainst",optional = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Ruler against;

    /**
     * Sets leader of the fraction
     * @throws NullValidationException if a person is null
     * @throws ContentViolationException if a person provided is not a member of a fraction
     * @param leader
     */
    public void setLeader(Person leader){
        if(leader == null){
            throw new NullValidationException("Fraction leader cannot be null");
        }
        if(rebels.contains(leader)){
            this.leader = leader;
            return;
        }
        throw new ContentViolationException("The leader is not a member of fraction");
    }

    /**
     * Adds a person to the list of participants of the following fraction
     * Cannot set add a person that the fraction was formed against
     * @throws ContentViolationException if a person is added that the fraction was created against
     * @throws NullValidationException if a person provided is null
     * @param rebel Person
     */
    public void addRebel(Person rebel) {
        if(rebel == null){
            throw new NullValidationException("Rebel cannot be null");
        }
        if(rebel == against){
            throw new ContentViolationException("Person cannot be in fraction against himself");
        }
        rebels.add(rebel);
    }

    /**
     * Create fraction use case
     * Creates fraction
     */
    public static void createFraction(){
        // FIXME: 6/18/2021 Unimplemented use case
    }

}
