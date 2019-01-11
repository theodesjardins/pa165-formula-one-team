package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.mvc.config.security.AuthenticationFacade;
import cz.muni.fi.pa165.mvc.config.security.SecurityRole;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.inject.Inject;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public abstract class BaseController {

    @Inject
    protected AuthenticationFacade authenticationFacade;

    @ModelAttribute("lang")
    public String getLocale() {
        return LocaleContextHolder.getLocale().getLanguage();
    }

    protected boolean userCanEdit() {
        return authenticationFacade.isAuthenticated()
                && (authenticationFacade.hasRole(SecurityRole.MANAGER));
    }
}
