package pja.edu.pl.darth.c0mp1ler.finalProject.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.enums.LatDir;
import pja.edu.pl.darth.c0mp1ler.finalProject.models.enums.LongDir;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Complex Attribute representing coordinates of of some MapComponent
 * @see MapComponent
 */
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Coordinates {

    @Min(0)
    private float latitude;

    @Min(0)
    private float longitude;

    @NotNull
    private LatDir latDir;

    @NotNull
    private LongDir longDir;
}
