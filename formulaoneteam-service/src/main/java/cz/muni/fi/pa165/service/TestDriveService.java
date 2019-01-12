package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.entity.CarSetup;
import cz.muni.fi.pa165.entity.Driver;
import cz.muni.fi.pa165.entity.TestDrive;
import cz.muni.fi.pa165.service.base.BaseEntityService;

import java.util.List;
import java.util.Map;

/**
 * @author Adel Chakouri
 */
public interface TestDriveService extends BaseEntityService<TestDrive> {

    Map<CarSetup, List<String>> getNotesForDriver(Driver driver);

    Map<Driver, List<String>> getNotesForCar(CarSetup car);
}
