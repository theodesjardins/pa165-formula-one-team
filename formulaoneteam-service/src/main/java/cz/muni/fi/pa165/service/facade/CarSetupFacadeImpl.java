package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.carsetup.CarSetupDTO;
import cz.muni.fi.pa165.dto.carsetup.SaveCarSetupDTO;
import cz.muni.fi.pa165.entity.CarSetup;
import cz.muni.fi.pa165.facade.CarSetupFacade;
import cz.muni.fi.pa165.service.CarSetupService;
import cz.muni.fi.pa165.service.ComponentService;
import cz.muni.fi.pa165.service.RaceParticipationService;
import cz.muni.fi.pa165.service.TestDriveService;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import cz.muni.fi.pa165.service.facade.base.BaseEntityFacadeImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Objects;

/**
 * @author Théo Desjardins
 */
@Service
@Transactional
public class CarSetupFacadeImpl
        extends BaseEntityFacadeImpl<CarSetupDTO, SaveCarSetupDTO, CarSetup, CarSetupService>
        implements CarSetupFacade {

    @Inject
    private ComponentService componentService;
    @Inject
    private TestDriveService testDriveService;
    @Inject
    private RaceParticipationService raceParticipationService;

    @Override
    public long add(SaveCarSetupDTO dto) {
        return service
                .add(mapDtoToEntity(dto))
                .getId();
    }

    @Override
    public void update(SaveCarSetupDTO dto, long id) {
        CarSetup car = mapDtoToEntity(dto);
        car.setId(id);

        service.update(car);
    }

    @Override
    public void remove(long id) {
        CarSetup carSetup = service.findById(id);

        if (hasTestDrive(carSetup) || hasRace(carSetup)) {
            throw new FormulaOneTeamException("Car can't be deleted when is part of an existing test drive or race.");
        }
        super.remove(id);
    }

    @Override
    protected Class<CarSetupDTO> getDtoClass() {
        return CarSetupDTO.class;
    }

    @Override
    protected Class<CarSetup> getEntityClass() {
        return CarSetup.class;
    }

    private CarSetup mapDtoToEntity(SaveCarSetupDTO dto) {
        return new CarSetup(
                componentService.findById(dto.getEngineId()),
                componentService.findById(dto.getSuspensionId()),
                componentService.findById(dto.getBrakesId()),
                componentService.findById(dto.getTransmissionId()),
                componentService.findById(dto.getTiresId()),
                componentService.findById(dto.getCoverId())
        );
    }

    private boolean hasRace(CarSetup carSetup) {
        return raceParticipationService.getAll().stream().anyMatch(raceParticipation -> Objects.equals(raceParticipation.getCarSetup(), carSetup));
    }

    private boolean hasTestDrive(CarSetup carSetup) {
        return testDriveService
                .getAll()
                .stream()
                .anyMatch(testDrive -> Objects.equals(testDrive.getCarSetup(), carSetup));
    }
}
