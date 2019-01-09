package cz.muni.fi.pa165.service.base;

import cz.muni.fi.pa165.dao.base.Dao;
import cz.muni.fi.pa165.entity.base.BaseEntity;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;

import java.util.Set;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public abstract class BaseEntityServiceImpl<E extends BaseEntity, DAO extends Dao<E>>
        extends BaseServiceImpl<E, DAO> implements BaseEntityService<E> {

    @Override
    public E add(E entity) throws FormulaOneTeamException {
        dao.add(entity);

        if (entity == null) throw new FormulaOneTeamException("Entity is null");
        return entity;
    }

    @Override
    public Set<E> add(Set<E> entities) throws FormulaOneTeamException {
        for (E entity: entities) {
            add(entity);
        }

        return entities;
    }
}
