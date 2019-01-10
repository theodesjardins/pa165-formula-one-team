package cz.muni.fi.pa165.service.base;

import cz.muni.fi.pa165.entity.base.BaseEntity;
import cz.muni.fi.pa165.exceptions.EntityNotFoundException;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public interface BaseService<E extends BaseEntity> {

    @NonNull
    E findById(long id) throws EntityNotFoundException;

    @NonNull
    List<E> getAll();

    void remove(long id);
}
