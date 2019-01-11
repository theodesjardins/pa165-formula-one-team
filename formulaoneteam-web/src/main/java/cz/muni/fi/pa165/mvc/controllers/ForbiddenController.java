package cz.muni.fi.pa165.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
@Controller
@RequestMapping("/forbidden")
public class ForbiddenController extends BaseController {

    @GetMapping
    public String open(Model model, @RequestParam String message) {
        model.addAttribute("message", message);
        return "forbidden";
    }
}
