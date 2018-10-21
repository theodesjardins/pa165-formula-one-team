package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.AppContextConfig;
import cz.muni.fi.pa165.entity.Manager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
@ContextConfiguration(classes=AppContextConfig.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
@RunWith(MockitoJUnitRunner.class)
public class ManagerDaoTest extends AbstractTestNGSpringContextTests {

    @Mock
    private EntityManager entityManager = mock(EntityManager.class);

    @InjectMocks
    private ManagerDao managerDao = new ManagerDaoImpl();

    @Test
    public void onNewManagerAdded_persisIt() {
        //given
        Manager manager = mock(Manager.class);
        when(manager.getName()).thenReturn("testName");

        //when
        managerDao.add(manager);

        //then
        entityManager.persist(manager);
    }
}
