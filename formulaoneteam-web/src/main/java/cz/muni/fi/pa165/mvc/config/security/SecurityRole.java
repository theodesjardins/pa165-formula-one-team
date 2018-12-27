package cz.muni.fi.pa165.mvc.config.security;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public enum SecurityRole {
    USER("USER"), ENGINEER("ENGINEER"), MANAGER("ADMIN");

    private String role;

    SecurityRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
