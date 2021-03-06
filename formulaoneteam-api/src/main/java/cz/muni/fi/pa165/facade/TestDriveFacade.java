package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.carsetup.CarSetupDTO;
import cz.muni.fi.pa165.dto.driver.DriverDTO;
import cz.muni.fi.pa165.dto.testdrive.SaveTestDriveDTO;
import cz.muni.fi.pa165.dto.testdrive.TestDriveDTO;
import cz.muni.fi.pa165.entity.TestDrive;
import cz.muni.fi.pa165.facade.base.BaseEntityFacade;

import java.util.List;
import java.util.Map;

/**
 * @author Adel Chakouri
 */
public interface TestDriveFacade extends BaseEntityFacade<TestDriveDTO, SaveTestDriveDTO, TestDrive> {

    Map<CarSetupDTO, List<String>> getNotesForDriver(DriverDTO driverDto);

    Map<DriverDTO, List<String>> getNotesForCar(CarSetupDTO carDto);
}
