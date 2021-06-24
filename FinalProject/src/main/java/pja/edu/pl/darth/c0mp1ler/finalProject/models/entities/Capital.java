package pja.edu.pl.darth.c0mp1ler.finalProject.models.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity representing Capital that is a Region and a Construction at the same time
 * @see Region
 * @see Construction
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Capital extends Construction implements Region{

    @NotBlank
    private String climate;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Religions",joinColumns = @JoinColumn(name = "capital_id"))
    private Set<String> centreOfReligions = new HashSet<>();

    @NotNull
    @OneToOne
    @JoinColumn(name = "ruler_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Ruler ruler;

    /**
     * Method of use case display information
     * @return string with the required info
     */
    @Override
    public String displayInfo() {
        return super.displayInfo();
    }

    /**
     * Method calculating the wealth of Capital
     * @return float as the wealth of construction
     */
    @Override
    public float getWealth() {
        // FIXME: 6/18/2021 Unimplemented use case
        return super.getWealth();
    }
}
