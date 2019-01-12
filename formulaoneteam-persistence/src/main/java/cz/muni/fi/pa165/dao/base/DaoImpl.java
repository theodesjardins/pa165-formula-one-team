package cz.muni.fi.pa165.dao.base;

import cz.muni.fi.pa165.entity.base.BaseEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;

import java.util.List;
import java.util.Set;

/**
 * Implementation of general Dao operations.
 *
 * @param <T> Generic parameter for the Dao's entity type.
 * @author Ivan Dendis
 */
@Transactional
public abstract class DaoImpl<T extends BaseEntity> implements Dao<T> {

    @PersistenceContext
    protected EntityManager entityManager;
    
    protected static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Override
    public T findById(Long id) {
        return entityManager.find(getClassType(), id);
    }

    @Override
    public void add(@Nullable T entity) {
        validateEntity(entity);

        entityManager.persist(entity);
    }

    @Override
    public void delete(long id) {
        entityManager.remove(findById(id));
    }

    @Override
    public void update(T entity) {
        validateEntity(entity);

        T managed = findById(entity.getId());

        BeanUtils.copyProperties(entity, managed);

        entityManager.merge(managed);
    }

    @Override
    public List<T> findAll() {
        Class<T> classType = getClassType();

        return entityManager
                .createQuery("FROM " + classType.getName(), classType)
                .getResultList();
    }

    protected abstract Class<T> getClassType();

    private void validateEntity(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be null.");
        }

        Set<ConstraintViolation<T>> violations = validator.validate(entity);
        if (violations.size() != 0) {
            StringBuilder sb = new StringBuilder();

            for (ConstraintViolation<T> violation : violations) {
                sb.append("'");
                sb.append(violation.getPropertyPath());
                sb.append("' ");
                sb.append(violation.getMessage());
                sb.append(System.lineSeparator());
            }

            throw new ValidationException(sb.toString());
        }
    }
}
