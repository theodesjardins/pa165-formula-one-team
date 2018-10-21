package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Manager;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

import static cz.muni.fi.pa165.entity.base.User.NAME_FIELD;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
@Repository
public class ManagerDaoImpl implements ManagerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Manager findById(Long id) {
        return entityManager.find(Manager.class, id);
    }

    @Override
    public void add(@Nullable Manager manager) {
        if (manager == null) {
            throw new IllegalArgumentException("Manager is null.");
        }
        if (manager.getName() == null || manager.getName().isEmpty()) {
            throw new IllegalArgumentException("Manager has no name assigned.");
        }

        entityManager.persist(manager);
    }

    @Override
    public void delete(@Nullable Manager manager) {
        if (manager == null) {
            throw new IllegalArgumentException("Manager is null");
        }

        entityManager.remove(manager);
    }

    @Override
    public void update(@Nullable Manager manager) {
        if (manager == null) {
            throw new IllegalArgumentException("Manager is null.");
        }
        if (manager.getName() == null || manager.getName().isEmpty()) {
            throw new IllegalArgumentException("Manager has no name assigned.");
        }

        entityManager.merge(manager);
    }

    @Override
    public List<Manager> findAll() {
        return entityManager
                .createQuery("select m from TestManager m", Manager.class)
                .getResultList();
    }

    @Nullable
    @Override
    public Manager findByName(String name) {
        try {
            return entityManager
                    .createQuery("select m from TestManager m where name = :name", Manager.class)
                    .setParameter(NAME_FIELD, name)
                    .getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        }
    }
}
