package pe.edu.cibertec.DAWI_Barazorda_Astete_PabloLarry.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class FilmCategory {

    @Id
    private Integer film_id;
    private Integer category_id;
    private Date last_update;

}
