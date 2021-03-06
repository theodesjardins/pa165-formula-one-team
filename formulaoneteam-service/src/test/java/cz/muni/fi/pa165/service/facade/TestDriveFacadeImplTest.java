package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.carsetup.CarSetupDTO;
import cz.muni.fi.pa165.dto.driver.DriverDTO;
import cz.muni.fi.pa165.dto.testdrive.SaveTestDriveDTO;
import cz.muni.fi.pa165.dto.testdrive.TestDriveDTO;
import cz.muni.fi.pa165.entity.CarSetup;
import cz.muni.fi.pa165.entity.Driver;
import cz.muni.fi.pa165.entity.TestDrive;
import cz.muni.fi.pa165.service.CarSetupService;
import cz.muni.fi.pa165.service.DriverService;
import cz.muni.fi.pa165.service.TestDriveService;
import cz.muni.fi.pa165.service.base.BaseFacadeTest;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Adel Chakouri
 */
public class TestDriveFacadeImplTest extends BaseFacadeTest<TestDrive, TestDriveDTO> {

    @Mock
    private DriverService driverService;

    @Mock
    private CarSetupService carSetupService;

    @Mock
    private TestDriveService service;

    @InjectMocks
    private TestDriveFacadeImpl testDriveFacade;

    @Override
    public void setUp() {
        super.setUp();

        when(beanMappingServiceMock.mapTo(dto, TestDrive.class)).thenReturn(entity);
        when(service.add(any(TestDrive.class))).thenReturn(entity);
    }

    @Test
    public void getAllTestDriveTest() {
        //Given
        List<TestDrive> listTestDrive = new ArrayList<>();
        listTestDrive.add(entity);
        when(service.getAll()).thenReturn(listTestDrive);
        List<TestDriveDTO> listDTOTestDrive = new ArrayList<>();
        listDTOTestDrive.add(dto);
        when(beanMappingServiceMock.mapTo(listTestDrive, TestDriveDTO.class)).thenReturn(listDTOTestDrive);

        //When
        List<TestDriveDTO> resListTestDriveDTO = new ArrayList<>(testDriveFacade.getAll());

        //Then
        verify(service).getAll();
        assertEquals(resListTestDriveDTO.size(), 1);
        Assert.assertTrue(resListTestDriveDTO.contains(dto));
    }

    @Test
    public void findTestDriveByIdTest() {
        //Given
        when(beanMappingServiceMock.mapTo(entity, TestDriveDTO.class)).thenReturn(dto);
        when(service.findById(22)).thenReturn(entity);

        //When
        TestDriveDTO resTestDriveDTO = testDriveFacade.findById(entity.getId());

        //Then
        assertEquals(resTestDriveDTO, dto);
    }

    @Test
    public void deleteTestDriveTest() {
        when(service.findById(dto.getId())).thenReturn(entity);

        //When
        testDriveFacade.remove(dto.getId());

        //Then
        verify(service, times(1)).remove(entity.getId());
    }

    @Test
    public void updateTestDriveTest() {
        //given
        SaveTestDriveDTO dto = createSaveTestDriveDTO();

        //When
        testDriveFacade.update(dto, 1);

        //then
        verify(carSetupService).findById(dto.getCarSetupId());
        verify(driverService).findById(dto.getDriverId());

        verify(service).update(any(TestDrive.class));
    }

    @Test
    public void addTestDriveTest() {
        //given
        SaveTestDriveDTO dto = createSaveTestDriveDTO();

        //when
        when(service.add(entity)).thenReturn(entity);

        long id = testDriveFacade.add(dto);

        //then
        verify(carSetupService).findById(dto.getCarSetupId());
        verify(driverService).findById(dto.getDriverId());

        verify(service).add(any(TestDrive.class));

        assertEquals(dto.getId(), id);
    }

    @Test
    public void whenGetNotesForDriver_beanMappingIsCalled() {
        //given
        Driver driver = createDriver();
        DriverDTO DriverDTO = createDriverDTO();

        Map<CarSetup, List<String>> notes = new HashMap<>();

        //when
        when(driverService.findById(DriverDTO.getId())).thenReturn(driver);
        when(service.getNotesForDriver(driver)).thenReturn(notes);

        testDriveFacade.getNotesForDriver(DriverDTO);

        //then
        verify(beanMappingServiceMock).mapTo(notes, CarSetupDTO.class);
    }

    @Test
    public void whenGetNotesForCarSetup_beanMappingIsCalled() {
        //given
        CarSetup carSetup = createCarSetup();
        CarSetupDTO carSetupDTO = createCarSetupDTO();
        Map<Driver, List<String>> notes = new HashMap<>();

        //when
        when(carSetupService.findById(carSetupDTO.getId())).thenReturn(carSetup);
        when(service.getNotesForCar(carSetup)).thenReturn(notes);

        testDriveFacade.getNotesForCar(carSetupDTO);

        //then
        verify(beanMappingServiceMock).mapTo(notes, DriverDTO.class);
    }

    @Override
    protected TestDrive createTestEntity() {
        return createTestDrive();
    }

    @Override
    protected TestDriveDTO createTestDTO() {
        return createTestDriveDTO();
    }
}
