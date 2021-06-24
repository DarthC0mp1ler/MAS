package pja.edu.pl.darth.c0mp1ler.finalProject.models.entities;

import lombok.*;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.enums.BuildingType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity representing a building in some construction
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @NotNull
    private BuildingType buildingType;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "construction_id",nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Construction construction;

    /**
     * Method for Create building use case
     * Creates building
     */
    public static void createBuilding(){
        // FIXME: 6/18/2021 Unimplemented use case
    }

    /**
     * Mathod for the destroy building use case
     * Destroys a particular building
     */
    public void destroyBuilding(){
        // FIXME: 6/18/2021 Unimplemented use case
    }

}
