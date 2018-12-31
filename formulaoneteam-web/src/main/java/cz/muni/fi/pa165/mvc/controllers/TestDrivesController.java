package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.testdrive.SaveTestDriveDTO;
import cz.muni.fi.pa165.dto.testdrive.TestDriveDTO;
import cz.muni.fi.pa165.facade.CarSetupFacade;
import cz.muni.fi.pa165.facade.DriverFacade;
import cz.muni.fi.pa165.facade.TestDriveFacade;
import cz.muni.fi.pa165.mvc.config.security.SecurityRole;
import cz.muni.fi.pa165.mvc.utils.Navigator;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
@Controller
@RequestMapping("/test-drives")
class TestDrivesController extends BaseController {

    @Inject
    private TestDriveFacade testDriveFacade;
    @Inject
    private CarSetupFacade carSetupFacade;
    @Inject
    private DriverFacade driverFacade;

    @RequestMapping
    public String list(Model model) {
        model.addAttribute("testDrives", testDriveFacade.getAll());
        return "test-drives/list";
    }

    @RequestMapping("/detail/{id}")
    public String detail(Model model, @PathVariable long id) {
        model.addAttribute("testDrive", testDriveFacade.findById(id));
        return "test-drives/detail";
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        if (userCanEdit()) {
            TestDriveDTO testDriveDTO = testDriveFacade.findById(id);
            SaveTestDriveDTO saveTestDriveDTO = new SaveTestDriveDTO(testDriveDTO);

            model.addAttribute("saveTestDrive", saveTestDriveDTO);
            model.addAttribute("testDrive", testDriveDTO);
            model.addAttribute("cars", carSetupFacade.getAll());
            model.addAttribute("drivers", driverFacade.getAll());

            return "test-drives/edit";
        } else {
            return Navigator.openForbiddenPage("Only manager can edit test drives.");
        }
    }

    @PostMapping(value = "/submit")
    public String submit(
            @Valid @ModelAttribute("saveTestDrive") SaveTestDriveDTO saveTestDriveDTO,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return saveTestDriveDTO.getId() == 0 ?
                    "redirect:test-drives/create" : "redirect:test-drives/edit/" + saveTestDriveDTO.getId();
        }

        if (saveTestDriveDTO.getId() == 0) {
            testDriveFacade.add(saveTestDriveDTO);
        } else {
            testDriveFacade.update(saveTestDriveDTO, saveTestDriveDTO.getId());
        }

        return "redirect:/test-drives";
    }

    @PostMapping(value = "/delete/{id}")
    public String delete(@PathVariable long id, RedirectAttributes redirectAttributes) {
        try {
            testDriveFacade.remove(id);
        } catch (FormulaOneTeamException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/test-drives/detail/" + id;
        }
        return "redirect:/test-drives";
    }

    @GetMapping("/create")
    public String create(Model model) {
        if (authenticationFacade.hasRole(SecurityRole.MANAGER)) {
            model.addAttribute("saveTestDrive", new SaveTestDriveDTO());
            model.addAttribute("cars", carSetupFacade.getAll());
            model.addAttribute("drivers", driverFacade.getAll());
            return "test-drives/edit";
        } else {
            return Navigator.openForbiddenPage("Only manager can create new test drives.");
        }
    }
}
