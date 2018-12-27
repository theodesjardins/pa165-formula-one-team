package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.driver.DriverDTO;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.facade.DriverFacade;
import cz.muni.fi.pa165.mvc.config.security.AuthenticationFacade;
import cz.muni.fi.pa165.mvc.config.security.SecurityRole;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author mrnda (Michal Mrnuštík)
 */
@Controller
@RequestMapping("/drivers")
public class DriversController {

    @Inject
    private DriverFacade driverFacade;

    @Inject
    private AuthenticationFacade authenticationFacade;

    private static final SimpleDateFormat birthdayDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @RequestMapping("/list")
    public String list(Model model) {
        model.addAttribute("drivers", driverFacade.getAllDrivers());
        return "drivers/list";
    }

    @RequestMapping("/detail/{id}")
    public String detail(Model model, @PathVariable long id) {
        final DriverDTO driver = driverFacade.findById(id);
        model.addAttribute("driver", driver);
        model.addAttribute("editingEnabled", userCanEditDriver(driver));
        return "drivers/detail";
    }

    @RequestMapping("/create")
    public String create(Model model) {
        if (authenticationFacade.hasRole(SecurityRole.MANAGER)) {
            model.addAttribute("driver", driverFacade.createDefaultDriver());
            return "drivers/edit";
        } else {
            throw new AccessDeniedException("Only manager can create new users.");
        }
    }

    @RequestMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        DriverDTO driver = driverFacade.findById(id);
        if (userCanEditDriver(driver)) {
            model.addAttribute("driver", driver);
            return "drivers/edit";
        } else {
            throw new AccessDeniedException("You can't edit this user!");
        }
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String submitDriver(@Valid @ModelAttribute("driver") DriverDTO driver, BindingResult bindingResult) {
        try {
            driver.setBirthday(birthdayDateFormat.parse(driver.getBirthdayString()));
        } catch (ParseException e) {
            bindingResult.addError(new FieldError("driver", "birthdayString", "has invalid format"));
        }

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

    private boolean userCanEditDriver(DriverDTO driver) {
        return authenticationFacade.isAuthenticated()
                && (authenticationFacade.hasRole(SecurityRole.MANAGER)
                || authenticationFacade.getCurrentUserEmail().equals(driver.getEmail()));
    }


}
