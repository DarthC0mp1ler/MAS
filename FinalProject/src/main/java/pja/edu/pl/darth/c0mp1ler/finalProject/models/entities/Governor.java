package pja.edu.pl.darth.c0mp1ler.finalProject.models.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pja.edu.pl.darth.c0mp1ler.finalProject.exceptions.NullValidationException;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity representing a governor
 * @see Person
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Governor extends Person{

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Managing_skills",joinColumns = @JoinColumn(name = "governor_id"))
    @Builder.Default
    private final Set<String> managingSkills = new HashSet<>();

    @OneToOne(mappedBy = "governor", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private GoverningContract contract;

    @OneToOne(mappedBy = "governor")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Setter(AccessLevel.NONE)
    private RegionImpl region;

    @ManyToOne
    @JoinColumn(name = "council_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Setter(AccessLevel.NONE)
    private Kingdom council;

    @OneToMany(mappedBy = "governor")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private final Set<Landlord> landlords = new HashSet<>();


    /**
     * Sets a region to be governed
     * If this governor is a member of a council it removes him from the council
     * @throws NullValidationException if a region provided is empty
     * @param region a region for this governor to govern
     * @see Kingdom
     * @see RegionImpl
     */
    public void setRegion(RegionImpl region){
        if(region == null){
            throw new NullValidationException("Region cannot be empty");
        }
        if(this.council != null) {
            this.council.getCouncil().remove(this);
            this.council = null;
        }
        this.region = region;
        region.setGovernor(this);
    }

    /**
     * Sets this governor to the provided council of the kingdom
     * if this governor is already governing some region the region gets removed
     * the region requires a governor though
     * @throws NullValidationException if a kingdom provided is null
     * @param council a kingdom for governor to be a councillor in
     * @see Kingdom
     * @see RegionImpl
     */
    public void setCouncil(Kingdom council){
        if(council == null){
            throw new NullValidationException("Council cannot be null");
        }
        if(this.region != null) {
            this.region.setGovernor(null);
            this.region = null;
        }
        this.council = council;
        council.getCouncil().add(this);
    }


}
