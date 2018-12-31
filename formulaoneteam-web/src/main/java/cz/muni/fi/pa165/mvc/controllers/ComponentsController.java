package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.ComponentDTO;
import cz.muni.fi.pa165.dto.ComponentParameterDTO;
import cz.muni.fi.pa165.enums.ComponentType;
import cz.muni.fi.pa165.facade.ComponentFacade;
import cz.muni.fi.pa165.mvc.utils.Navigator;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Arrays;

import static cz.muni.fi.pa165.mvc.config.security.SecurityRole.ENGINEER;
import static cz.muni.fi.pa165.mvc.config.security.SecurityRole.MANAGER;


/**
 * @author Théo Desjardins
 */
@Controller
@RequestMapping("/components")
class ComponentsController extends BaseController {

    @Inject
    private ComponentFacade componentFacade;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("components", componentFacade.getAll());
        return "components/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        if (authenticationFacade.hasRole(MANAGER) || authenticationFacade.hasRole(ENGINEER)) {
            ComponentDTO componentDTO = new ComponentDTO();
            componentDTO.setParameters(Arrays.asList(
                    new ComponentParameterDTO(), new ComponentParameterDTO()
            ));

            model.addAttribute("component", componentDTO);
            return "components/edit";
        } else {
            return Navigator.openForbiddenPage("Only managers or engineers can create new components.");
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        if (userCanEdit()) {
            ComponentDTO component = componentFacade.findById(id);
            model.addAttribute("component", component);
            return "components/edit";
        } else {
            return Navigator.openForbiddenPage("Only managers or engineers can edit components.");
        }
    }

    @PostMapping(value = "/submit")
    public String submitComponent(@Valid @ModelAttribute("component") ComponentDTO component, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "components/edit";
        }

        if (component.getId() == 0) {
            componentFacade.add(component);
        } else {
            componentFacade.update(component, component.getId());
        }
        return "redirect:/components";
    }

    @PostMapping(value = "/delete/{id}")
    public String delete(@PathVariable long id, RedirectAttributes redirectAttributes) {
        try {
            componentFacade.remove(id);
        } catch (FormulaOneTeamException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/components/detail/" + id;
        }
        return "redirect:/components";
    }

    @ModelAttribute("componentTypeValues")
    public ComponentType[] getComponentTypeValues() {
        return ComponentType.values();
    }

    @Override
    protected String onGetDetail(Model model, long id) {
        model.addAttribute("component", componentFacade.findById(id));
        return "components/detail";
    }

    @Override
    protected boolean userCanEdit() {
        return super.userCanEdit() || (authenticationFacade.hasRole(ENGINEER));
    }
}