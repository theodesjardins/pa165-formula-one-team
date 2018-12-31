package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.carsetup.CarSetupDTO;
import cz.muni.fi.pa165.dto.carsetup.SaveCarSetupDTO;
import cz.muni.fi.pa165.entity.CarSetup;
import cz.muni.fi.pa165.service.CarSetupService;
import cz.muni.fi.pa165.service.ComponentService;
import cz.muni.fi.pa165.service.RaceParticipationService;
import cz.muni.fi.pa165.service.TestDriveService;
import cz.muni.fi.pa165.service.base.BaseFacadeTest;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Théo Desjardins
 */
public class CarSetupFacadeImplTest extends BaseFacadeTest<CarSetup, CarSetupDTO> {
    
    @Mock
    private CarSetupService carSetupService;
    @Mock
    private ComponentService componentService;
    @Mock
    private TestDriveService testDriveService;
    @Mock
    private RaceParticipationService raceParticipationService;

    @InjectMocks
    private CarSetupFacadeImpl carSetupFacade;

    @Override
    public void setUp() {
        super.setUp();
        ReflectionTestUtils.setField(carSetupFacade, "service", carSetupService);

        when(beanMappingServiceMock.mapTo(dto, CarSetup.class)).thenReturn(entity);
    }

    @Test
    public void findCarSetupById() {
        //Given
        when(carSetupService.findById(entity.getId())).thenReturn(entity);
        when(beanMappingServiceMock.mapTo(entity, CarSetupDTO.class)).thenReturn(dto);
        //When
        CarSetupDTO carSetupDTO = carSetupFacade.findById(entity.getId());
        //Then
        assertEquals(carSetupDTO, dto);
    }

    @Test
    public void deleteCarSetupTest() {
        //when
        carSetupFacade.remove(dto.getId());

        //then
        verify(carSetupService, times(1)).remove(entity.getId());
    }

    @Test
    public void updateCarSetupTest() {
        when(componentService.findById(1)).thenReturn(createComponent());
        //when
        SaveCarSetupDTO saveDto = new SaveCarSetupDTO();
        saveDto.setBrakesId(1);
        saveDto.setCoverId(1);
        saveDto.setEngineId(1);
        saveDto.setId(1);
        saveDto.setSuspensionId(1);
        saveDto.setTiresId(1);
        saveDto.setTransmissionId(1);

        carSetupFacade.update(saveDto, 1);

        //then
        verify(carSetupService, times(1)).update(entity);
    }

    @Test
    public void addCarSetupTest() {
        when(componentService.findById(1)).thenReturn(createComponent());
        //when
        when(carSetupService.add(entity)).thenReturn(entity);
        
        SaveCarSetupDTO saveDto = new SaveCarSetupDTO();
        saveDto.setBrakesId(1);
        saveDto.setCoverId(1);
        saveDto.setEngineId(1);
        saveDto.setId(1);
        saveDto.setSuspensionId(1);
        saveDto.setTiresId(1);
        saveDto.setTransmissionId(1);

        carSetupFacade.add(saveDto);

        //then
        verify(carSetupService, times(1)).add(entity);
    }

    @Test
    public void getAllCarSetupTest() {

        //Given
        List<CarSetup> listCarSetup = new ArrayList<>();
        listCarSetup.add(entity);
        when(carSetupService.getAll()).thenReturn(listCarSetup);
        List<CarSetupDTO> listDTOCarSetup = new ArrayList<>();
        listDTOCarSetup.add(dto);
        when(beanMappingServiceMock.mapTo(listCarSetup, CarSetupDTO.class)).thenReturn(listDTOCarSetup);

        //When
        List<CarSetupDTO> resListdto = new ArrayList<>(carSetupFacade.getAll());

        //Then
        verify(carSetupService).getAll();
        assertEquals(resListdto.size(), 1);
        Assert.assertTrue(resListdto.contains(dto));
    }

    @Override
    protected CarSetup createTestEntity() {
        return createCarSetup();
    }

    @Override
    protected CarSetupDTO createTestDTO() {
        return createCarSetupDTO();
    }
}
