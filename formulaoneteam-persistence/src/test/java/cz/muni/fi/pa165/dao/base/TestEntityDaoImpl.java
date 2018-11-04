package cz.muni.fi.pa165.dao.base;

import javax.persistence.EntityManager;

/**
 * Dao implementation used to test common Dao operations.
 *
 * @author Ivan Dendis
 */
public class TestEntityDaoImpl extends DaoImpl<TestEntity> {

    private Boolean validateCalled;

    public TestEntityDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Boolean getValidateCalled() {
        return validateCalled;
    }

    @Override
    protected void validateEntity(TestEntity entity) {
        validateCalled = true;
    }

    @Override
    protected Class<TestEntity> getClassType() {
        return TestEntity.class;
    }
}
