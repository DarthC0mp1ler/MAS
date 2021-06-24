package pja.edu.pl.darth.c0mp1ler.finalProject.models.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.enums.ConstructionStatus;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.enums.ConstructionType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity representing construction that extends MapComponent
 * @see MapComponent
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Construction extends MapComponent{

    @NotNull(message = "Construction date must not be null")
    private LocalDate constructionDate;

    private LocalDate destructionDate;

    @NotNull(message = "Construction type must not be null")
    private ConstructionType constructionType;

    @NotNull(message = "Construction status must not be null")
    private ConstructionStatus constructionStatus;

    @Transient
    private Period getAge(){
        return Period.between(constructionDate,LocalDate.now());
    }

    @NotNull
    @ManyToOne
    @JoinColumn(name = "region_id",nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private RegionImpl region;

    @OneToMany(mappedBy = "construction", cascade = CascadeType.REMOVE)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Building> buildings = new HashSet<>();

    @NotNull
    @ManyToOne
    @JoinColumn(name = "landlord_id",nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Landlord landlord;

    /**
     * Displaying information use case
     * @return the sting with the information to be shown
     */
    @Override
    public String displayInfo() {
        return "Lands of " + getLandlord().getName() + " " + getLandlord().getFamilyName() + "\n" +
                "Constructed on " + getConstructionDate()  +" \n"  +
                "Type: " + getConstructionType() + "\n" +
                "Status: " + getConstructionStatus();
    }

    /**
     * Get wealth use case
     * @return float with the wealth of construciton
     */
    @Override
    public float getWealth() {
        switch (constructionStatus){
            case Prosperous:
                return 50;
            case Inspired:
                return 25;
            case Raided:
                return -25;
            case Starving:
                return -50;
            case Destroyed:
                return -75;
            default:
                return 0;
        }
    }

    /**
     * Method created for the change of status as effect of dynamic analysis
     * Increases the status by one
     */
    public void helpLocals(){
        // FIXME: 6/18/2021 Unimplemented use case
    }

    /**
     * Method created for the change of status as effect of dynamic analysis
     * Decreases the status to raided and then each time by one
     */
    public void raid(){
        // FIXME: 6/18/2021 Unimplemented use case
    }

}
