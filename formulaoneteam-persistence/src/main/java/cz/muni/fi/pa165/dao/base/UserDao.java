package cz.muni.fi.pa165.dao.base;

import cz.muni.fi.pa165.entity.base.User;

/**
 * Interface for generic user Dao operations.
 *
 * @param <T> Generic parameter for the Dao's entity type.
 * @author Ivan Dendis
 */
public interface UserDao<T extends User> extends Dao<T> {

    /**
     * Finds an entity extending {@code User} by its email.
     * @param email address to find.
     * @return found entity object or null if email was not found.
     */
    T findByEmail(String email);
}
