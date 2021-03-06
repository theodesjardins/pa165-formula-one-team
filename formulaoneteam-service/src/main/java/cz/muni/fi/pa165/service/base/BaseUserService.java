package cz.muni.fi.pa165.service.base;

import cz.muni.fi.pa165.entity.base.BaseEntity;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public interface BaseUserService<T extends BaseEntity> extends BaseService<T> {

    T register(T entity, String unencryptedPassword);

    boolean authenticate(String email, String password);

    T findByEmail(String email);
}
