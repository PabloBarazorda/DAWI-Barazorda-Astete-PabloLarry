package pe.edu.cibertec.DAWI_Barazorda_Astete_PabloLarry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DAWI_Barazorda_Astete_PabloLarry.dto.*;
import pe.edu.cibertec.DAWI_Barazorda_Astete_PabloLarry.service.MaintenanceService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/maintenance")
public class MaintenanceController {
    @Autowired
    MaintenanceService maintenanceService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/start")
    public String start(Model model) {


        List<FilmListadoDto> films = maintenanceService.getAllFilms();
        model.addAttribute("films", films);
        return "maintenance";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        FilmDetallesDto filmDetallesDto = maintenanceService.getFilmById(id);

        model.addAttribute("filmDetailDto", filmDetallesDto);
        return "maintenance-detalles";
    }


    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        FilmDetallesDto filmDetallesDto = maintenanceService.getFilmById(id);
        model.addAttribute("filmActualizarDto", new FilmActualizarDto(
                filmDetallesDto.filmId(),
                filmDetallesDto.title(),
                filmDetallesDto.description(),
                filmDetallesDto.releaseYear(),
                filmDetallesDto.rentalDuration(),
                filmDetallesDto.rentalRate(),
                filmDetallesDto.length(),
                filmDetallesDto.replacementCost(),
                filmDetallesDto.rating(),
                filmDetallesDto.specialFeatures(),
                filmDetallesDto.lastUpdate()
        ));
        return "maintenance-actualizar";
    }


    @PostMapping("/update")
    public String updateFilm(@ModelAttribute FilmActualizarDto filmActualizarDto, Model model) {
        FilmDetallesDto updatedFilm = maintenanceService.updateFilmDetails(filmActualizarDto);
        model.addAttribute("filmDetallesDto", updatedFilm);
        return "redirect:/maintenance/start";
    }


    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("filmCrearDto", new FilmCrearDto(null, null, null, null, null, null, null, null, null, null, null, null, null));
        return "maintenance-crear";
    }

    @PostMapping("/create")
    public String createFilm(@ModelAttribute FilmCrearDto filmCrearDto, Model model) {
        maintenanceService.createFilm(filmCrearDto);
        return "redirect:/maintenance/start";
    }

    @PostMapping("/delete")
    public String deleteFilm(@ModelAttribute FilmEliminarDto filmEliminarDto) {
        maintenanceService.deleteFilm(filmEliminarDto);
        return "redirect:/maintenance/start";
    }

}
