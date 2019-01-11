package cz.muni.fi.pa165.service.base;

import cz.muni.fi.pa165.entity.base.BaseEntity;
import cz.muni.fi.pa165.exceptions.EntityNotFoundException;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public interface BaseService<E extends BaseEntity> {

    void remove(long id);

    E update(@Nullable E entity);

    @NonNull
    E findById(long id) throws EntityNotFoundException;

    @NonNull
    List<E> getAll();

}