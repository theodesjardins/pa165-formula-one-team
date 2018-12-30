package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.carsetup.CarSetupDTO;
import cz.muni.fi.pa165.dto.driver.DriverDTO;
import cz.muni.fi.pa165.dto.race.RaceDTO;
import cz.muni.fi.pa165.dto.raceparticipation.RaceParticipationDTO;
import cz.muni.fi.pa165.dto.raceparticipation.SaveRaceParticipationDTO;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.facade.CarSetupFacade;
import cz.muni.fi.pa165.facade.DriverFacade;
import cz.muni.fi.pa165.facade.RaceFacade;
import cz.muni.fi.pa165.facade.RaceParticipationFacade;
import cz.muni.fi.pa165.mvc.config.security.SecurityRole;
import cz.muni.fi.pa165.mvc.utils.Navigator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Adel Chakouri
 */
@Controller
@RequestMapping("/world-championship")
public class WorldChampionshipController extends BaseController {

    @Inject
    private RaceParticipationFacade raceParticipationFacade;

    @Inject
    private RaceFacade raceFacade;

    @Inject
    private DriverFacade driverFacade;

    @Inject
    private CarSetupFacade carSetupFacade;

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("raceParticipations", raceParticipationFacade.getAll());
        return "world-championship/list";
    }

    @RequestMapping("/detail/{id}")
    public String detail(Model model, @PathVariable long id) {
        model.addAttribute("raceParticipation", raceParticipationFacade.findById(id));
        return "world-championship/detail";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        RaceParticipationDTO raceParticipationDTO = raceParticipationFacade.findById(id);
        model.addAttribute("saveRaceParticipation", new SaveRaceParticipationDTO(raceParticipationDTO));
        return "world-championship/edit";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        if (authenticationFacade.hasRole(SecurityRole.MANAGER)) {
            SaveRaceParticipationDTO dto = new SaveRaceParticipationDTO();
            dto.setRaceDTO(new RaceDTO());

            model.addAttribute("saveRaceParticipation", dto);
            return "world-championship/edit";
        } else {
            return Navigator.openForbiddenPage("Only manager can create new race participations.");
        }
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submitDriver(
            @Valid @ModelAttribute("saveRaceParticipation") SaveRaceParticipationDTO dto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "world-championship/edit";
        }

        RaceDTO raceDTO = dto.getRaceDTO();

        long raceId = raceDTO.getId();
        if (raceId == 0) {
            raceFacade.add(raceDTO);
        } else {
            raceFacade.update(raceDTO, raceId);
        }

        if (dto.getId() == 0) {
            raceParticipationFacade.add(dto);
        } else {
            raceParticipationFacade.update(dto, dto.getId());
        }
        return "redirect:/world-championship/list";
    }

    @ModelAttribute("carSetups")
    public List<CarSetupDTO> getCarSetups() {
        return carSetupFacade.getAll();
    }

    @ModelAttribute("mainDrivers")
    public List<DriverDTO> getMainDrivers() {
        return driverFacade.getAllDriversByStatus(DriverStatus.MAIN);
    }
}
