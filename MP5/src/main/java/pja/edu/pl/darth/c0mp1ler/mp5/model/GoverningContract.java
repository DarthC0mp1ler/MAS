package pja.edu.pl.darth.c0mp1ler.mp5.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"landlord_id","governor_id"}))
public class GoverningContract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @OneToOne
    @JoinColumn(name = "landlord_id",nullable = false)
    private Landlord landlord;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "governor_id",nullable = false)
    private Governor governor;

    @NotNull
    private LocalDate signDate;

    @Min(1)
    private double tax;
}
