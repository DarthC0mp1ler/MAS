package pja.edu.pl.darth.c0mp1ler.finalProject.models.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pja.edu.pl.darth.c0mp1ler.finalProject.exceptions.ContentViolationException;
import pja.edu.pl.darth.c0mp1ler.finalProject.exceptions.NullValidationException;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing map component
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class MapComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "color",joinColumns = @JoinColumn(name = "component_id"))
    @Builder.Default
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Integer> color = new ArrayList<>(3);

    @Embedded
    @NotNull
    private Coordinates coordinates;

    /**
     * Setter for the color variable
     * sets 3 first integers from the provided list and clamps them between 0 and 255
     * @param col list of integers
     */
    public void setColor(List<Integer> col){
        List<Integer> color = new ArrayList<>(3);
        if(col == null){
            throw new NullValidationException("Color cannot be null");
        }
        if(col.size() < this.color.size()){
            throw new ContentViolationException("Color should be represented by 3 numbers");
        }
        for (int i = 0; i < this.color.size(); i++) {
            color.add(i,Math.max(Math.min(col.get(i), 255), 0));
        }
        this.color = color;
    }

    public abstract String displayInfo();

    public abstract float getWealth();

}
