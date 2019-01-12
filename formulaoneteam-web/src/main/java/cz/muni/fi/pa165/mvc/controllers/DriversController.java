package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.driver.DriverDTO;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.facade.DriverFacade;
import cz.muni.fi.pa165.mvc.config.security.SecurityRole;
import cz.muni.fi.pa165.mvc.utils.Navigator;
import cz.muni.fi.pa165.utils.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * @author mrnda (Michal Mrnuštík)
 */
@Controller
@RequestMapping("/drivers")
public class DriversController extends BaseDetailController {

    @Inject
    private DriverFacade driverFacade;

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("drivers", driverFacade.getAll());
        return "drivers/list";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        if (authenticationFacade.hasRole(SecurityRole.MANAGER)) {
            model.addAttribute("driver", driverFacade.createDefaultDriver());
            return "drivers/edit";
        } else {
            return Navigator.openForbiddenPage("Only manager can create new users.");
        }
    }

    @Override
    protected String onEdit(Model model, long id) {
        model.addAttribute("driver", driverFacade.findById(id));
        return "drivers/edit";
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submitDriver(@Valid @ModelAttribute("driver") DriverDTO driver, BindingResult bindingResult) {
        driver.setBirthday(DateUtils.parseDate(driver.getBirthdayString()));

        if (!driver.getPassword().equals(driver.getConfirmPassword())) {
            bindingResult.addError(new FieldError("driver", "password", "does not match"));
            bindingResult.addError(new FieldError("driver", "confirmPassword", "does not match"));
        }

        final boolean isNewUser = driver.getId() == 0;
        if (driver.getPassword().isEmpty() && isNewUser) {
            bindingResult.addError(new FieldError("driver", "password", "is required"));
        }

        if (bindingResult.hasErrors()) {
            return "drivers/edit";
        }

        if (isNewUser) {
            driverFacade.register(driver, driver.getPassword());
        } else {
            driverFacade.updateDriver(driver);
        }
        return "redirect:/drivers/list";
    }

    @ModelAttribute("driverStatusValues")
    public DriverStatus[] getDriverStatusValues() {
        return DriverStatus.values();
    }

    @Override
    protected String onGetDetail(Model model, long id) {
        final DriverDTO driver = driverFacade.findById(id);
        model.addAttribute("driver", driver);
        model.addAttribute("editingEnabled", userCanEdit(id));
        return "drivers/detail";
    }

    @Override
    protected boolean userCanEdit(long id) {
        return authenticationFacade.isAuthenticated()
                && (authenticationFacade.hasRole(SecurityRole.MANAGER)
                || authenticationFacade.getCurrentUserEmail().equals(driverFacade.findById(id).getEmail()));
    }
}
