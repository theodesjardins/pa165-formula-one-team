package cz.muni.fi.pa165.mvc.config.security;

import cz.muni.fi.pa165.dto.AuthenticateDTO;
import cz.muni.fi.pa165.facade.DriverFacade;
import cz.muni.fi.pa165.facade.EngineerFacade;
import cz.muni.fi.pa165.facade.ManagerFacade;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

import static cz.muni.fi.pa165.mvc.config.security.SecurityRole.*;
import static java.util.Collections.singletonList;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
@Component
public class AuthProvider implements AuthenticationProvider {

    @Inject
    private ManagerFacade managerFacade;

    @Inject
    private DriverFacade driverFacade;

    @Inject
    private EngineerFacade engineerFacade;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = (String) authentication.getCredentials();
        SecurityRole role;

        AuthenticateDTO authenticateDTO = new AuthenticateDTO();
        authenticateDTO.setEmail(email);
        authenticateDTO.setPassword(password);

        if (managerFacade.authenticate(authenticateDTO)) {
            role = MANAGER;
        } else if (engineerFacade.authenticate(authenticateDTO)) {
            role = ENGINEER;
        } else if (driverFacade.authenticate(authenticateDTO)) {
            role = USER;
        } else {
            throw new BadCredentialsException("Invalid credentials");
        }

        return new UsernamePasswordAuthenticationToken(
                email, password, singletonList(new SimpleGrantedAuthority(role.getRole()))
        );
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
