package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.raceparticipation.RaceParticipationDTO;
import cz.muni.fi.pa165.dto.WorldChampionshipSetupDTO;
import cz.muni.fi.pa165.dto.raceparticipation.SaveRaceParticipationDTO;
import cz.muni.fi.pa165.entity.CarSetup;
import cz.muni.fi.pa165.entity.Driver;
import cz.muni.fi.pa165.entity.RaceParticipation;
import cz.muni.fi.pa165.service.CarSetupService;
import cz.muni.fi.pa165.service.DriverService;
import cz.muni.fi.pa165.service.RaceParticipationService;
import cz.muni.fi.pa165.service.RaceService;
import cz.muni.fi.pa165.service.base.BaseFacadeTest;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Adel Chakouri
 */
public class RaceParticipationFacadeImplTest extends BaseFacadeTest<RaceParticipation, RaceParticipationDTO> {

    @Mock
    private RaceService raceService;
    @Mock
    private CarSetupService carSetupService;
    @Mock
    private DriverService driverService;
    @Mock
    private RaceParticipationService service;

    @InjectMocks
    private RaceParticipationFacadeImpl facade;

    @Override
    public void setUp() {
        super.setUp();

        when(beanMappingServiceMock.mapTo(dto, RaceParticipation.class)).thenReturn(entity);
        when(service.add(any(RaceParticipation.class))).thenReturn(entity);
    }

    @Test
    public void getAllRaceParticipationTest() {
        //Given
        List<RaceParticipation> listRaceParticipation = new ArrayList<>();
        listRaceParticipation.add(entity);
        when(service.getAll()).thenReturn(listRaceParticipation);
        List<RaceParticipationDTO> listDTORaceParticipation = new ArrayList<>();
        listDTORaceParticipation.add(dto);
        when(beanMappingServiceMock.mapTo(listRaceParticipation, RaceParticipationDTO.class)).thenReturn(listDTORaceParticipation);

        //When
        List<RaceParticipationDTO> resListRaceParticipationDTO = new ArrayList<>(facade.getAll());

        //Then
        verify(service).getAll();
        assertEquals(resListRaceParticipationDTO.size(), 1);
        Assert.assertTrue(resListRaceParticipationDTO.contains(dto));
    }

    @Test
    public void findRaceParticipationById() {
        //Given
        when(beanMappingServiceMock.mapTo(entity, RaceParticipationDTO.class)).thenReturn(dto);
        when(service.findById(1)).thenReturn(entity);

        //When
        RaceParticipationDTO resRaceParticipationDTO = facade.findById(entity.getId());

        //Then
        assertEquals(resRaceParticipationDTO, dto);
    }

    @Test
    public void deleteRaceParticipationTest() {
        //When
        facade.remove(dto.getId());

        //Then
        verify(service, times(1)).remove(entity.getId());
    }

    @Test
    public void updateRaceParticipationTest() {
        //given
        SaveRaceParticipationDTO dto = createUpdateRaceParticipationDTO();

        //When
        facade.update(dto, 1);

        //Then
        verify(service).update(any(RaceParticipation.class));
    }

    @Test
    public void addRaceParticipationTest() {
        //given
        SaveRaceParticipationDTO dto = createUpdateRaceParticipationDTO();

        //when
        when(service.add(entity)).thenReturn(entity);

        long id = facade.add(dto);

        //then
        verify(carSetupService).findById(dto.getCarSetupId());
        verify(driverService).findById(dto.getDriverId());
        verify(raceService).findById(dto.getRaceId());

        verify(service).add(any(RaceParticipation.class));

        assertEquals(dto.getId(), id);
    }

    @Test(expected = IllegalArgumentException.class)
    public void participateInWorldChampionship_withNullValue_throwsException() {
        //When
        facade.participateInWorldChampionship(null);
    }

    @Test
    public void participateInWorldChampionship_withValidData_createsTheChampionship() {
        //Given
        final WorldChampionshipSetupDTO worldChampionshipDTO = createWorldChampionshipDTO();
        final Driver driverEntity = createDriver();
        final CarSetup carSetupEntity = createCarSetup();
        when(beanMappingServiceMock.mapTo(worldChampionshipDTO.getFirstDriver(), Driver.class)).thenReturn(driverEntity);
        when(beanMappingServiceMock.mapTo(worldChampionshipDTO.getSecondDriver(), Driver.class)).thenReturn(driverEntity);
        when(beanMappingServiceMock.mapTo(worldChampionshipDTO.getFirstCarSetup(), CarSetup.class)).thenReturn(carSetupEntity);
        when(beanMappingServiceMock.mapTo(worldChampionshipDTO.getSecondCarSetup(), CarSetup.class)).thenReturn(carSetupEntity);

        //When
        final List<RaceParticipationDTO> raceParticipationDTOS = facade.participateInWorldChampionship(worldChampionshipDTO);

        //Then
        verify(service, times(1))
                .participateInWorldChampionship(worldChampionshipDTO.getDate(),
                        worldChampionshipDTO.getLocation(),
                        Arrays.asList(Pair.of(carSetupEntity, driverEntity), Pair.of(carSetupEntity, driverEntity)));
    }

    private WorldChampionshipSetupDTO createWorldChampionshipDTO() {
        final WorldChampionshipSetupDTO worldChampionshipSetupDTO = new WorldChampionshipSetupDTO();
        worldChampionshipSetupDTO.setDate(createDate(2, 11, 2019));
        worldChampionshipSetupDTO.setLocation("Barcelona");
        worldChampionshipSetupDTO.setFirstCarSetup(createCarSetupDTO());
        worldChampionshipSetupDTO.setSecondCarSetup(createCarSetupDTO());
        worldChampionshipSetupDTO.setFirstDriver(createDriverDTO());
        worldChampionshipSetupDTO.setSecondDriver(createDriverDTO());
        return worldChampionshipSetupDTO;
    }

    @Override
    protected RaceParticipation createTestEntity() {
        return createRaceParticipation();
    }

    @Override
    protected RaceParticipationDTO createTestDTO() {
        return createRaceParticipationDTO();
    }
}
