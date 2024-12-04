package pe.edu.cibertec.DAWI_Barazorda_Astete_PabloLarry.service;

import pe.edu.cibertec.DAWI_Barazorda_Astete_PabloLarry.dto.*;

import java.util.List;

public interface MaintenanceService {

    List<FilmListadoDto> getAllFilms();

    FilmDetallesDto getFilmById(int id);

    FilmDetallesDto updateFilmDetails(FilmActualizarDto filmActualizarDto);

    FilmDetallesDto createFilm(FilmCrearDto filmCrearDto);

    void deleteFilm(FilmEliminarDto filmEliminarDto);
}
