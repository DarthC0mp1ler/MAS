package pja.edu.pl.darth.c0mp1ler.finalProject.models.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity representing a region
 * @see MapComponent
 * @see Region
 */
@Entity(name = "Region")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RegionImpl extends MapComponent implements Region{

    @NonNull
    private String climate;

    @ManyToOne
    @JoinColumn(name = "kingdom_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Kingdom kingdom;

    @OneToMany(mappedBy = "region")
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Construction> constructions = new HashSet<>();

    @NotNull
    @OneToOne(optional = false)
    @JoinColumn(name = "governor_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Governor governor;

    /**
     * Display info use case
     * @return information to be displayed as string
     */
    @Override
    public String displayInfo() {
        return "";
    }

    /**
     * Calculates total wealth of the region
     * @return float with wealth of region
     */
    @Override
    public float getWealth() {
        float wealth = 0;
        for (Construction c: constructions) {
            wealth += c.getWealth();
        }
        return wealth;
    }

    @Override
    public String toString() {
        return "Region of " + getName();
    }
}
