package cz.muni.fi.pa165.mvc.config.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationFacadeImpl implements AuthenticationFacade {
    @Override
    public boolean isAuthenticated() {
        return getAuthentication() != null;
    }

    @Override
    public String getCurrentUserEmail() {
        return getAuthentication().getName();
    }

    @Override
    public boolean hasRole(SecurityRole role) {
        final Authentication authentication = getAuthentication();
        if (authentication == null)
            return false;
        return authentication.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals(role.getRole()));
    }


    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
