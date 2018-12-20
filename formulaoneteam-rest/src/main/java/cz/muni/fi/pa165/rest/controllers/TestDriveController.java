package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.TestDriveDTO;
import cz.muni.fi.pa165.dto.rest.RestTestDriveDTO;
import cz.muni.fi.pa165.dto.carsetup.CarSetupDTO;
import cz.muni.fi.pa165.dto.driver.DriverDetailDTO;
import cz.muni.fi.pa165.entity.CarSetup;
import cz.muni.fi.pa165.entity.Driver;
import cz.muni.fi.pa165.entity.TestDrive;
import cz.muni.fi.pa165.facade.CarSetupFacade;
import cz.muni.fi.pa165.facade.DriverFacade;
import cz.muni.fi.pa165.facade.TestDriveFacade;
import cz.muni.fi.pa165.rest.ApiUris;
import cz.muni.fi.pa165.rest.controllers.base.BaseEntityController;
import javassist.NotFoundException;

import javax.inject.Inject;

import org.hibernate.annotations.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author Adel Chakouri
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_TEST_DRIVE)
public class TestDriveController {
    
    @Inject
    private TestDriveFacade facade;
    @Inject
    private CarSetupFacade carFacade;
    @Inject
    private DriverFacade driverFacade;
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TestDriveDTO> findById(@PathVariable long id) {
        return ResponseEntity.ok(facade.findById(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity remove(@PathVariable long id) {
        facade.remove(id);
        return ResponseEntity.accepted().build();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TestDriveDTO> add(@RequestBody RestTestDriveDTO dto) {
        if (dto.getDriverId() < 1 || dto.getCarId() < 1 ) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        CarSetupDTO setup = carFacade.findById(dto.getCarId());
        DriverDetailDTO driver = driverFacade.findById(dto.getDriverId());

        if (setup == null || driver == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        TestDriveDTO newDto = new TestDriveDTO();
        newDto.setCarSetup(setup);
        newDto.setDriver(driver);
        newDto.setNotes(dto.getNotes());
        newDto.setDate(dto.getDate());

        long id = facade.add(newDto);
        return ResponseEntity.ok(facade.findById(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@RequestBody RestTestDriveDTO dto, @PathVariable long id) {
        TestDriveDTO targetDto = facade.findById(id);

        if (dto.getDriverId() < 1 || dto.getCarId() < 1 ) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        CarSetupDTO setup = carFacade.findById(dto.getCarId());
        DriverDetailDTO driver = driverFacade.findById(dto.getDriverId());

        if (setup == null || driver == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        targetDto.setNotes(dto.getNotes());
        targetDto.setDate(dto.getDate());
        targetDto.setCarSetup(setup);
        targetDto.setDriver(driver);

        facade.update(targetDto, id);
        return ResponseEntity.ok(facade.findById(id));
    }
}
