package cz.muni.fi.pa165.dao.base;

import cz.muni.fi.pa165.entity.base.User;

/**
 * Interface for generic user Dao operations.
 *
 * @param <T> Generic parameter for the Dao's entity type.
 * @author Ivan Dendis
 */
public interface UserDao<T extends User> extends Dao<T> {

    T findByEmail(String email);
}
