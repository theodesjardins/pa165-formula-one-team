package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.testdrive.SaveTestDriveDTO;
import cz.muni.fi.pa165.dto.testdrive.TestDriveDTO;
import cz.muni.fi.pa165.entity.TestDrive;
import cz.muni.fi.pa165.facade.TestDriveFacade;
import cz.muni.fi.pa165.rest.ApiUris;
import cz.muni.fi.pa165.rest.controllers.base.EntityController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Adel Chakouri
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_TEST_DRIVE)
class TestDriveController extends EntityController<TestDriveFacade, TestDriveDTO, SaveTestDriveDTO, TestDrive> {
}
