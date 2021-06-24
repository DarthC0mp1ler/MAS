package pja.edu.pl.darth.c0mp1ler.finalProject.models.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity representing a landlord
 * @see Person
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Landlord extends Person{

    @Min(0)
    private int previousGenerations;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "governor_id",nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Governor governor;

    @OneToMany(mappedBy = "landlord")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private final Set<Construction> constructions = new HashSet<>();

}
