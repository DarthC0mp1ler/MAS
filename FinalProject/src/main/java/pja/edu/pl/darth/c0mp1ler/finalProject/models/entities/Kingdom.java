package pja.edu.pl.darth.c0mp1ler.finalProject.models.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pja.edu.pl.darth.c0mp1ler.finalProject.exceptions.NullValidationException;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * An entity representing a kingdom
 * @see MapComponent
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Kingdom extends MapComponent{

    private static final float BASE_POWER_PER_REGION = 50;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "ruler_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Ruler ruler;

    @OneToOne
    @JoinColumn(name = "regent_id")
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Ruler regent;

    @OneToMany(mappedBy = "kingdom")
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<RegionImpl> regions = new HashSet<>();

    @Size(max=7)
    @OneToMany(mappedBy = "council")
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Governor> council = new HashSet<>();

    /**
     * setting regent of kingdom
     * it is possible only if a ruler is under age
     * @param ruler a regent for the kingdom
     */
    public void setRegent(Ruler ruler){
        if(ruler == null){
            regent = null;
            return;
        }
        if(this.ruler.getAge().getYears() < 18){
            regent = ruler;
        }
    }

    /**
     * Display information use case
     * @return information to be displayed
     */
    @Override
    public String displayInfo() {
        return "";
    }

    /**
     * calculates total wealth of the kingdom
     * @return float with the total wealth of the kingdom
     */
    @Override
    public float getWealth() {
        float wealth = 0;
        for (RegionImpl c: regions) {
            wealth += c.getWealth();
        }
        return wealth;
    }

    /**
     * Derived attribute that returns the power of kingdom
     * @return power of kingdom
     */
    @Transient
    public float getPower(){
        return BASE_POWER_PER_REGION * regions.size();
    }

    @Override
    public String toString() {
        return "Kingdom of " + getName();
    }
}
