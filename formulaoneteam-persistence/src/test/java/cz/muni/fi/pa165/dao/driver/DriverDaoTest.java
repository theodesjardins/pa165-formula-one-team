package cz.muni.fi.pa165.dao.driver;

import cz.muni.fi.pa165.dao.base.BaseTest;
import cz.muni.fi.pa165.entity.Driver;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;

public class DriverDaoTest extends BaseTest {

    @Inject
    private DriverDao driverDao;

    @Test
    public void addDriver_withValidData_isPersisted() {
        //given
        Driver driver = createDriver(getDefaultDriverEmail());

        //when
        driverDao.add(driver);

        //then
        assertNotNull(driverDao.findById(driver.getId()));
    }
}
