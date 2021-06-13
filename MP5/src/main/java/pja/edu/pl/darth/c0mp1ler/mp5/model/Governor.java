package pja.edu.pl.darth.c0mp1ler.mp5.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
public class Governor extends Person{

    @NotBlank(message = "governing region is mandatory")
    @Size(min = 3, max = 250)
    private String governingRegionName;

    @OneToMany(mappedBy = "governor",cascade = CascadeType.REMOVE)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<GoverningContract> contracts = new HashSet<>();

}
