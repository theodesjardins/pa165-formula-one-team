package cz.muni.fi.pa165.dao.manager;

import cz.muni.fi.pa165.entity.Manager;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

import static cz.muni.fi.pa165.entity.base.User.EMAIL_FIELD;

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
        checkManager(manager);

        entityManager.persist(manager);
    }

    @Override
    public void delete(@Nullable Manager manager) {
        checkManager(manager);

        entityManager.remove(manager);
    }

    @Override
    public void update(@Nullable Manager manager) {
        checkManager(manager);

        entityManager.merge(manager);
    }

    @Override
    public List<Manager> findAll() {
        return entityManager
                .createQuery("select manager from Manager manager", Manager.class)
                .getResultList();
    }

    @Nullable
    @Override
    public Manager findByEmail(String email) {
        try {
            return entityManager
                    .createQuery("select manager from Manager manager where email = :email", Manager.class)
                    .setParameter(EMAIL_FIELD, email)
                    .getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        }
    }

    private void checkManager(Manager manager) {
        if (manager == null || !manager.isConfigured()) {
            throw new IllegalArgumentException("Manager is null or not configured");
        }
    }
}
