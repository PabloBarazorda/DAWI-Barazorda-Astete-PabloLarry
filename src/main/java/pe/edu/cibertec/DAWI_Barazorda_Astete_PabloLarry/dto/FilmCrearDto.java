package pe.edu.cibertec.DAWI_Barazorda_Astete_PabloLarry.dto;

import java.util.Date;

public record FilmCrearDto(Integer filmId,
                           String title,
                           String description,
                           Integer releaseYear,
                           Integer languageId,
                           String languageName,
                           Integer rentalDuration,
                           Double rentalRate,
                           Integer length,
                           Double replacementCost,
                           String rating,
                           String specialFeatures,
                           Date lastUpdate) {
}
