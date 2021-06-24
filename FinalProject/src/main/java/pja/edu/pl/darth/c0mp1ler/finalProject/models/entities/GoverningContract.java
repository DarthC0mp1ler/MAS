package pja.edu.pl.darth.c0mp1ler.finalProject.models.entities;

import lombok.*;
import pja.edu.pl.darth.c0mp1ler.finalProject.exceptions.ContentViolationException;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Entity representing contract between ruler and a governor
 * @see Governor
 * @see Ruler
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"ruler_id","governor_id"}))
public class GoverningContract {

    private static final float MAX_CHANGE_PERCENT = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private LocalDate signDate;

    private LocalDate terminationDate;

    @Min(0)
    @Setter(AccessLevel.NONE)
    private float tax;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ruler_id", nullable = false)
    private Ruler ruler;

    @NotNull
    @OneToOne
    @JoinColumn(name = "governor_id", nullable = false)
    private Governor governor;

    /**
     * sets tax of the contract
     * the value cannot be changed by more that 10% at once but can be decreased by any amount
     * @param tax
     */
    public void setTax(float tax){
        if(this.tax != 0 && tax > this.tax/MAX_CHANGE_PERCENT + this.tax){
            throw new ContentViolationException("Tax cannot be increased by more than 10%");
        }
        this.tax = tax;
    }


}
