package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.exceptions.EntityNotFoundException;
import cz.muni.fi.pa165.mvc.utils.Navigator;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public abstract class BaseDetailController extends BaseController {

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable long id) {
        try {
            return onGetDetail(model, id);
        } catch (EntityNotFoundException e) {
            return Navigator.openNotFoundPage("The resource is not found");
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        if (userCanEdit(id)) {
            return onEdit(model, id);
        } else {
            return Navigator.openForbiddenPage("You have no permissions to edit.");
        }
    }

    protected abstract String onEdit(Model model, @PathVariable long id);

    protected abstract String onGetDetail(Model model, long id);
}
