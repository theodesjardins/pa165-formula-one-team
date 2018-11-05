package cz.muni.fi.pa165.dao.base;

import cz.muni.fi.pa165.entity.base.User;

import static cz.muni.fi.pa165.entity.base.User.EMAIL_FIELD;

import javax.persistence.NoResultException;

/**
 * Implementation of general user Dao operations.
 *
 * @param <T> Generic parameter for the Dao's entity type.
 * @author Ivan Dendis
 */
public abstract class UserDaoImpl<T extends User> extends DaoImpl<T>
        implements UserDao<T> {

    @Override
    public T findByEmail(String email) {
        try {
            String query = "FROM " + getClassType().getName() + " WHERE email = :email";

            return entityManager.createQuery(query, getClassType())
                    .setParameter(EMAIL_FIELD, email)
                    .getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        }
    }
}
