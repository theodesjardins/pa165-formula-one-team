package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.race.RaceDTO;
import cz.muni.fi.pa165.entity.Race;
import cz.muni.fi.pa165.facade.RaceFacade;
import cz.muni.fi.pa165.service.RaceService;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import cz.muni.fi.pa165.service.facade.base.EntityFacadeImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Adel Chakouri
 */
@Service
@Transactional
public class RaceFacadeImpl extends EntityFacadeImpl<RaceDTO, Race, RaceService> implements RaceFacade {

    @Override
    public void remove(long id) {
        final Race race = service.findById(id);
        if (!race.getRaceParticipations().isEmpty()) {
            throw new FormulaOneTeamException("Race can't be deleted because there are drivers participating in it.");
        }
        super.remove(id);
    }

    @Override
    protected Class<RaceDTO> getDtoClass() {
        return RaceDTO.class;
    }

    @Override
    protected Class<Race> getEntityClass() {
        return Race.class;
    }
}
