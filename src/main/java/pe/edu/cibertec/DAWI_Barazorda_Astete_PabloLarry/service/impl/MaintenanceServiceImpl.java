package pe.edu.cibertec.DAWI_Barazorda_Astete_PabloLarry.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.DAWI_Barazorda_Astete_PabloLarry.dto.*;
import pe.edu.cibertec.DAWI_Barazorda_Astete_PabloLarry.entity.Film;
import pe.edu.cibertec.DAWI_Barazorda_Astete_PabloLarry.entity.Language;
import pe.edu.cibertec.DAWI_Barazorda_Astete_PabloLarry.repository.FilmRepository;
import pe.edu.cibertec.DAWI_Barazorda_Astete_PabloLarry.service.MaintenanceService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    @Autowired
    FilmRepository filmRepository;


    @Override
    public List<FilmListadoDto> getAllFilms() {

        List<FilmListadoDto> films = new ArrayList<FilmListadoDto>();
        Iterable<Film> iterable = filmRepository.findAll();
        iterable.forEach(film -> {
            FilmListadoDto filmListadoDto = new FilmListadoDto(film.getFilmId(),
                    film.getTitle(),
                    film.getLanguage().getName(),
                    film.getRentalRate());
            films.add(filmListadoDto);
        });

        return films;
    }

    @Override
    public FilmDetallesDto getFilmById(int id) {
        Optional<Film> optional = filmRepository.findById(id);
        return optional.map(
                film -> new FilmDetallesDto(
                        film.getFilmId(),
                        film.getTitle(),
                        film.getDescription(),
                        film.getReleaseYear(),
                        film.getLanguage().getName(),
                        film.getRentalDuration(),
                        film.getRentalRate(),
                        film.getLength(),
                        film.getReplacementCost(),
                        film.getRating(),
                        film.getSpecialFeatures(),
                        film.getLastUpdate())
        ).orElse(null);
    }

    @Override
    public FilmDetallesDto updateFilmDetails(FilmActualizarDto filmActualizarDto) {
        Optional<Film> filmOptional = filmRepository.findById(filmActualizarDto.filmId());
        if (filmOptional.isPresent()) {
            Film film = filmOptional.get();

            if (filmActualizarDto.title() != null) film.setTitle(filmActualizarDto.title());
            if (filmActualizarDto.description() != null) film.setDescription(filmActualizarDto.description());
            if (filmActualizarDto.releaseYear() != null) film.setReleaseYear(filmActualizarDto.releaseYear());


            if (filmActualizarDto.rentalDuration() != null) film.setRentalDuration(filmActualizarDto.rentalDuration());
            if (filmActualizarDto.rentalRate() != null) film.setRentalRate(filmActualizarDto.rentalRate());
            if (filmActualizarDto.length() != null) film.setLength(filmActualizarDto.length());
            if (filmActualizarDto.replacementCost() != null)
                film.setReplacementCost(filmActualizarDto.replacementCost());
            if (filmActualizarDto.rating() != null) film.setRating(filmActualizarDto.rating());
            if (filmActualizarDto.specialFeatures() != null)
                film.setSpecialFeatures(filmActualizarDto.specialFeatures());

            film.setLastUpdate(new java.util.Date());

            filmRepository.save(film);

            return new FilmDetallesDto(film.getFilmId(), film.getTitle(), film.getDescription(), film.getReleaseYear(),
                    film.getLanguage().getName(), film.getRentalDuration(), film.getRentalRate(),
                    film.getLength(), film.getReplacementCost(), film.getRating(), film.getSpecialFeatures(), film.getLastUpdate());
        } else {
            throw new RuntimeException("No se pudo encontrar la película " + filmActualizarDto.filmId());
        }
    }

    @Override
    public FilmDetallesDto createFilm(FilmCrearDto filmCrearDto) {
        Film film = new Film();
        film.setTitle(filmCrearDto.title());
        film.setDescription(filmCrearDto.description());
        film.setReleaseYear(filmCrearDto.releaseYear());
        film.setRentalDuration(filmCrearDto.rentalDuration());
        film.setRentalRate(filmCrearDto.rentalRate());
        film.setLength(filmCrearDto.length());
        film.setReplacementCost(filmCrearDto.replacementCost());
        film.setRating(filmCrearDto.rating());
        film.setSpecialFeatures(filmCrearDto.specialFeatures());
        film.setLastUpdate(filmCrearDto.lastUpdate());

        Language language = new Language();
        language.setLanguageId(filmCrearDto.languageId());
        language.setName(filmCrearDto.languageName());
        film.setLanguage(language);

        filmRepository.save(film);

        return new FilmDetallesDto(
                film.getFilmId(),
                film.getTitle(),
                film.getDescription(),
                film.getReleaseYear(),
                film.getLanguage().getName(),
                film.getRentalDuration(),
                film.getRentalRate(),
                film.getLength(),
                film.getReplacementCost(),
                film.getRating(),
                film.getSpecialFeatures(),
                film.getLastUpdate());
    }


    @Override
    public void deleteFilm(FilmEliminarDto filmEliminarDto) {
        filmRepository.deleteById(filmEliminarDto.filmId());
    }

}
