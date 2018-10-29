package cz.muni.fi.pa165.dao.manager;

import cz.muni.fi.pa165.AppContextConfig;
import cz.muni.fi.pa165.entity.Manager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.AssertJUnit;

import javax.inject.Inject;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
@ContextConfiguration(classes= AppContextConfig.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
public class ManagerDaoTest extends AbstractTestNGSpringContextTests {

    @Inject
    private ManagerDao managerDao;

    @Test
    public void onNewManagerAdded_persisIt() {
        //given
        Manager manager = new Manager();
        manager.setName("testName");
        manager.setSurname("test");
        manager.setEmail("test@email.com");
        manager.setPassword("test");

        //when
        managerDao.add(manager);

        //then
        AssertJUnit.assertNotNull(managerDao.findById(manager.getId()));
    }
}
