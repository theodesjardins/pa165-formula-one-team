package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.carsetup.CarSetupDTO;
import cz.muni.fi.pa165.dto.carsetup.SaveCarSetupDTO;
import cz.muni.fi.pa165.facade.CarSetupFacade;
import cz.muni.fi.pa165.facade.ComponentFacade;
import cz.muni.fi.pa165.facade.RaceParticipationFacade;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.validation.Valid;

import java.util.stream.Collectors;

import static cz.muni.fi.pa165.enums.ComponentType.*;

/**
 * @author Ivan Dendis
 */
@Controller
@RequestMapping("/cars")
public class CarsController extends BaseDetailController {

    @Inject
    private CarSetupFacade carFacade;
    @Inject
    private ComponentFacade componentFacade;
    @Inject
    private RaceParticipationFacade raceParticipationFacade;

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("cars", carFacade.getAll());

        return "cars/list";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        addComponentsData(model);

        CarSetupDTO defaultCar = new CarSetupDTO();
        model.addAttribute("car", defaultCar);

        return "cars/edit";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        addComponentsData(model);

        model.addAttribute("car", carFacade.findById(id));

        return "cars/edit";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submit(@Valid @ModelAttribute("car") SaveCarSetupDTO car, BindingResult bindingResult) {
        final boolean isNewCar = car.getId() == 0;

        if (bindingResult.hasErrors()) {
            return "cars/edit";
        }
        if (isNewCar) {
            carFacade.add(car);
        } else {
            carFacade.update(car, car.getId());
        }

        return "redirect:/cars/list";
    }

    @PostMapping(value = "/delete/{id}")
    public String delete(@PathVariable long id, RedirectAttributes redirectAttributes) {
        try {
            carFacade.remove(id);
        } catch (FormulaOneTeamException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/cars/detail/" + id;
        }
        return "redirect:/cars/list";
    }

    @Override
    protected String onGetDetail(Model model, long id) {
        CarSetupDTO car = carFacade.findById(id);

        car.setRaceParticipations(raceParticipationFacade.getAll()
                .stream()
                .filter(r -> r.getCarSetup() == car)
                .collect(Collectors.toList()));

        model.addAttribute("car", carFacade.findById(id));

        return "cars/detail";
    }

    private void addComponentsData(Model model) {
        model.addAttribute("engines", componentFacade.filterByType(ENGINE));
        model.addAttribute("suspensions", componentFacade.filterByType(SUSPENSION));
        model.addAttribute("brakes", componentFacade.filterByType(BRAKES));
        model.addAttribute("transmissions", componentFacade.filterByType(TRANSMISSION));
        model.addAttribute("tires", componentFacade.filterByType(TIRES));
        model.addAttribute("covers", componentFacade.filterByType(COVER));
    }
}
