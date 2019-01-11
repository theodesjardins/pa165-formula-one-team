package cz.muni.fi.pa165.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
@Controller
@RequestMapping("/")
public class HomeController extends BaseController {

    @GetMapping
    public String open() {
        return "home";
    }
}
