package cz.muni.fi.pa165.dao.engineer;

import cz.muni.fi.pa165.dao.base.BaseTest;
import cz.muni.fi.pa165.entity.Engineer;
import cz.muni.fi.pa165.enums.EngineerSpecialization;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static org.testng.AssertJUnit.*;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public class EngineerDaoTest extends BaseTest {

    private static String TEST_NAME = "testName";

    @Inject
    private EngineerDao engineerDao;

    private Engineer testEngineer;

    @Before
    public void setUp() {
        testEngineer = createTestEngineer();
    }

    @Test
    public void createNewEngineer_foundById() {
        //when
        engineerDao.add(testEngineer);

        //then
        assertEquals(TEST_NAME, engineerDao.findById(testEngineer.getId()).getName());
    }

    @Test
    public void createNewEngineer_foundInAllEngineers() {
        //when
        engineerDao.add(testEngineer);

        //then
        List<Engineer> engineers = engineerDao.findAll();
        assertEquals(1, engineers.size());
        assertTrue(engineers.contains(testEngineer));
    }

    @Test
    public void createMultipleNewEngineers_foundAll() {
        //given
        Engineer otherEngineer = new Engineer("otherName", "otherSurname", "other@email.com",
                "otherPassword", EngineerSpecialization.AERODYNAMICS);

        //when
        engineerDao.add(testEngineer);
        engineerDao.add(otherEngineer);

        //then
        final List<Engineer> engineers = engineerDao.findAll();
        assertEquals(2, engineers.size());
        assertTrue(engineers.contains(testEngineer));
        assertTrue(engineers.contains(otherEngineer));
    }

    @Test
    public void updateEngineer_engineerUpdated() {
        //given
        engineerDao.add(testEngineer);
        EngineerSpecialization newEngineerSpecialization = EngineerSpecialization.FLUID_MECHANICS;

        //when
        testEngineer.setSpecialization(newEngineerSpecialization);
        engineerDao.update(testEngineer);

        //then
        assertEquals(newEngineerSpecialization, engineerDao.findById(testEngineer.getId()).getSpecialization());
    }

    @Test
    public void removeEngineer_engineerNotFoundById() {
        //given
        engineerDao.add(testEngineer);

        //when
        engineerDao.delete(testEngineer);

        //then
        assertNull(engineerDao.findById(testEngineer.getId()));
    }

    @Test
    public void removeEngineer_engineerNotFoundAtAll() {
        //given
        engineerDao.add(testEngineer);

        //when
        engineerDao.delete(testEngineer);

        //then
        assertEquals(0, engineerDao.findAll().size());
    }

    private Engineer createTestEngineer() {
        String TEST_SURNAME = "testSurname";
        String TEST_EMAIL = "test@email.com";
        String TEST_PASSWORD = "testPassword";
        return new Engineer(TEST_NAME, TEST_SURNAME, TEST_EMAIL, TEST_PASSWORD, EngineerSpecialization.ENGINES);
    }
}
