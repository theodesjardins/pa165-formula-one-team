package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.mvc.config.security.AuthenticationFacade;
import cz.muni.fi.pa165.mvc.config.security.SecurityRole;

import javax.inject.Inject;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public class BaseController {

    @Inject
    protected AuthenticationFacade authenticationFacade;

    protected boolean userCanEdit() {
        return authenticationFacade.isAuthenticated()
                && (authenticationFacade.hasRole(SecurityRole.MANAGER));
    }
}
