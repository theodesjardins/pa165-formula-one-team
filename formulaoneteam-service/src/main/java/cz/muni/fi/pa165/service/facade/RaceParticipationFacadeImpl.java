package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.WorldChampionshipSetupDTO;
import cz.muni.fi.pa165.dto.raceparticipation.RaceParticipationDTO;
import cz.muni.fi.pa165.dto.raceparticipation.SaveRaceParticipationDTO;
import cz.muni.fi.pa165.entity.CarSetup;
import cz.muni.fi.pa165.entity.Driver;
import cz.muni.fi.pa165.entity.Race;
import cz.muni.fi.pa165.entity.RaceParticipation;
import cz.muni.fi.pa165.facade.RaceFacade;
import cz.muni.fi.pa165.facade.RaceParticipationFacade;
import cz.muni.fi.pa165.service.CarSetupService;
import cz.muni.fi.pa165.service.DriverService;
import cz.muni.fi.pa165.service.RaceParticipationService;
import cz.muni.fi.pa165.service.RaceService;
import cz.muni.fi.pa165.service.facade.base.BaseEntityFacadeImpl;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

/**
 * @author Adel Chakouri
 */
@Service
@Transactional
public class RaceParticipationFacadeImpl
        extends BaseEntityFacadeImpl<RaceParticipationDTO, SaveRaceParticipationDTO, RaceParticipation,
        RaceParticipationService>
        implements RaceParticipationFacade {

    @Inject
    private DriverService driverService;

    @Inject
    protected RaceFacade raceFacade;

    @Inject
    private RaceService raceService;

    @Inject
    protected CarSetupService carSetupService;

    @Override
    public long add(SaveRaceParticipationDTO dto) {
        return service
                .add(mapDtoToEntity(dto))
                .getId();
    }

    @Override
    public void update(SaveRaceParticipationDTO dto, long id) {
        RaceParticipation raceParticipation = mapDtoToEntity(dto);
        raceParticipation.setId(id);

        service.update(raceParticipation);
    }

    @Override
    public List<RaceParticipationDTO> participateInWorldChampionship(WorldChampionshipSetupDTO worldChampionshipSetupDTO) {
        if (worldChampionshipSetupDTO == null)
            throw new IllegalArgumentException("worldChampionshipSetupDTO can't be null");

        final Pair<CarSetup, Driver> firstDriverCarSetupPair =
                Pair.of(beanMappingService.mapTo(worldChampionshipSetupDTO.getFirstCarSetup(), CarSetup.class),
                        beanMappingService.mapTo(worldChampionshipSetupDTO.getFirstDriver(), Driver.class));

        final Pair<CarSetup, Driver> secondDriverCarSetupPair =
                Pair.of(beanMappingService.mapTo(worldChampionshipSetupDTO.getSecondCarSetup(), CarSetup.class),
                        beanMappingService.mapTo(worldChampionshipSetupDTO.getSecondDriver(), Driver.class));

        final List<RaceParticipation> raceParticipations =
                service.participateInWorldChampionship(
                        worldChampionshipSetupDTO.getDate(),
                        worldChampionshipSetupDTO.getLocation(),
                        Arrays.asList(firstDriverCarSetupPair, secondDriverCarSetupPair));

        return beanMappingService.mapTo(raceParticipations, RaceParticipationDTO.class);
    }

    @Override
    public void remove(long id) {
        final RaceParticipation participation = service.findById(id);
        removeParticipationFromRace(participation);
        removeParticipationFromCarSetup(participation);
        removeParticipationFromDriver(participation);
        service.remove(id);
    }

    @Override
    protected Class<RaceParticipationDTO> getDtoClass() {
        return RaceParticipationDTO.class;
    }

    @Override
    protected Class<RaceParticipation> getEntityClass() {
        return RaceParticipation.class;
    }

    private RaceParticipation mapDtoToEntity(SaveRaceParticipationDTO dto) {
        return new RaceParticipation(
                carSetupService.findById(dto.getCarSetupId()),
                driverService.findById(dto.getDriverId()),
                raceService.findById(raceFacade.add(dto.getRaceDTO())),
                dto.getResultPosition()
        );
    }

    private void removeParticipationFromRace(RaceParticipation participation) {
        final Race race = participation.getRace();
        race.getRaceParticipations().remove(participation);
        raceService.update(race);
    }

    private void removeParticipationFromCarSetup(RaceParticipation participation) {
        final CarSetup carSetup = participation.getCarSetup();
        carSetup.getRaceParticipations().remove(participation);
        carSetupService.update(carSetup);
    }

    private void removeParticipationFromDriver(RaceParticipation participation) {
        final Driver driver = participation.getDriver();
        driver.getRaceParticipations().remove(participation);
        driverService.update(driver);
    }
}

