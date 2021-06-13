package pja.edu.pl.darth.c0mp1ler.mp5.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;
    @NotNull
    private BuildingType type;

    @ManyToOne(optional = false)
    @JoinColumn(name = "construction_id", nullable = false, updatable = false)
    private Construction construction;

}
