package pja.edu.pl.darth.c0mp1ler.mp5.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Construction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 4, max = 50,message = "Name length must be within specified range")
    private String name;

    @NotNull(message = "Date is mandatory")
    private LocalDate constructionDate;

    @NotNull(message = "ConstructionType is mandatory")
    private ConstructionType constructionType;

    @ManyToOne
    @JoinColumn(name = "landlord_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Landlord manager;

    @OneToMany(mappedBy = "construction", cascade = CascadeType.REMOVE)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Building> buildings = new HashSet<>();

}
