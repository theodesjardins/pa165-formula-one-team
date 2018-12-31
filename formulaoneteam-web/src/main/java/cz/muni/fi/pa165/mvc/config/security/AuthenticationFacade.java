package cz.muni.fi.pa165.mvc.config.security;

import org.springframework.stereotype.Service;

@Service
public interface AuthenticationFacade {

    boolean isAuthenticated();

    String getCurrentUserEmail();

    boolean hasRole(SecurityRole role);
}
