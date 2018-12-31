package cz.muni.fi.pa165.rest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.muni.fi.pa165.dto.*;
import cz.muni.fi.pa165.dto.carsetup.CarSetupDTO;
import cz.muni.fi.pa165.dto.carsetup.SaveCarSetupDTO;
import cz.muni.fi.pa165.dto.driver.DriverDTO;
import cz.muni.fi.pa165.dto.race.RaceDTO;
import cz.muni.fi.pa165.dto.testdrive.SaveTestDriveDTO;
import cz.muni.fi.pa165.dto.testdrive.TestDriveDTO;
import cz.muni.fi.pa165.enums.ComponentType;
import cz.muni.fi.pa165.enums.DriverStatus;
import org.junit.Before;
import org.junit.Rule;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static cz.muni.fi.pa165.enums.EngineerSpecialization.AERODYNAMICS;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public abstract class BaseControllerTests<Controller> {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    protected MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = standaloneSetup(getController())
                .build();
    }

    protected AuthenticateDTO createAuthenticateDTO() {
        final AuthenticateDTO authDTO = new AuthenticateDTO();
        authDTO.setEmail("test@test.cz");
        authDTO.setPassword("password");
        return authDTO;
    }

    protected abstract Controller getController();

    protected Date createDate(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    protected String convertToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    protected DriverDTO createDriverDTO() {
        DriverDTO driverDetailDTO = new DriverDTO();
        driverDetailDTO.setId(22);
        driverDetailDTO.setName("John");
        driverDetailDTO.setSurname("Doe");
        driverDetailDTO.setEmail("John@doe.com");
        driverDetailDTO.setNationality("American");
        driverDetailDTO.setBirthday(createDate(2, 9, 1989));
        driverDetailDTO.setCharacteristics(new ArrayList<>());
        driverDetailDTO.setDriverStatus(DriverStatus.MAIN);
        return driverDetailDTO;
    }

    protected ComponentDTO createComponentDTO() {
        return createComponentDTO(ComponentType.ENGINE);
    }

    private ComponentDTO createComponentDTO(ComponentType type) {
        ComponentDTO componentDTO = new ComponentDTO();
        componentDTO.setId(1);
        componentDTO.setType(type);
        componentDTO.setName("test " + type.toString());
        return componentDTO;
    }

    protected CarSetupDTO createCarSetupDTO() {
        CarSetupDTO dto = new CarSetupDTO();
        dto.setId(1);
        dto.setEngine(createComponentDTO(ComponentType.ENGINE));
        dto.setSuspension(createComponentDTO(ComponentType.SUSPENSION));
        dto.setBrakes(createComponentDTO(ComponentType.BRAKES));
        dto.setCover(createComponentDTO(ComponentType.COVER));
        dto.setTires(createComponentDTO(ComponentType.TIRES));
        dto.setTransmission(createComponentDTO(ComponentType.TRANSMISSION));
        return dto;
    }

    protected SaveCarSetupDTO createSaveCarSetupDTO() {
        SaveCarSetupDTO dto = new SaveCarSetupDTO();
        dto.setId(1);
        dto.setEngineId(createComponentDTO(ComponentType.ENGINE).getId());
        dto.setSuspensionId(createComponentDTO(ComponentType.SUSPENSION).getId());
        dto.setBrakesId(createComponentDTO(ComponentType.BRAKES).getId());
        dto.setCoverId(createComponentDTO(ComponentType.COVER).getId());
        dto.setTiresId(createComponentDTO(ComponentType.TIRES).getId());
        dto.setTransmissionId(createComponentDTO(ComponentType.TRANSMISSION).getId());
        return dto;
    }

    protected RaceDTO createRaceDTO() {
        RaceDTO raceDTO = new RaceDTO();
        raceDTO.setId(3);
        raceDTO.setDate(new Date());
        raceDTO.setLocation("location");
        raceDTO.setTitle("GP Monaco");
        return raceDTO;
    }

    protected TestDriveDTO createTestDriveDTO() {
        TestDriveDTO testDriveDTO = new TestDriveDTO();
        testDriveDTO.setId(22);
        testDriveDTO.setCarSetup(createCarSetupDTO());
        testDriveDTO.setDate(new Date());
        testDriveDTO.setDriver(createDriverDTO());
        testDriveDTO.setNotes("notes");
        return testDriveDTO;
    }

    protected SaveTestDriveDTO createSaveTestDriveDTO() {
        SaveTestDriveDTO testDriveDTO = new SaveTestDriveDTO();
        testDriveDTO.setId(22);
        testDriveDTO.setCarSetupId(createCarSetupDTO().getId());
        testDriveDTO.setDate(new Date());
        testDriveDTO.setDriverId(createDriverDTO().getId());
        testDriveDTO.setNotes("notes");
        return testDriveDTO;
    }

    protected EngineerDTO createEngineerDTO() {
        EngineerDTO engineer = new EngineerDTO();
        engineer.setId(22);
        engineer.setName("name");
        engineer.setSurname("surname");
        engineer.setEmail("email@email.email");
        engineer.setSpecialization(AERODYNAMICS);
        engineer.setPassword("hash123");
        return engineer;
    }

    protected ManagerDTO createManagerDTO() {
        ManagerDTO manager = new ManagerDTO();
        manager.setId(22);
        manager.setName("name");
        manager.setSurname("surname");
        manager.setEmail("email@email.email");
        manager.setPassword("hash123");
        return manager;
    }

    protected ComponentParameterDTO createComponentParameterDTO() {
        ComponentParameterDTO dto = new ComponentParameterDTO();
        dto.setId(55);
        dto.setName("name");
        dto.setValue("value");
        return dto;
    }
}
