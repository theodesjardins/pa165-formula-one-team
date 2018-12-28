package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.WorldChampionshipSetupDTO;
import cz.muni.fi.pa165.dto.raceparticipation.RaceParticipationDTO;
import cz.muni.fi.pa165.dto.raceparticipation.SaveRaceParticipationDTO;
import cz.muni.fi.pa165.entity.RaceParticipation;
import cz.muni.fi.pa165.facade.base.BaseEntityFacade;

import java.util.List;

/**
 * @author Adel Chakouri
 */
public interface RaceParticipationFacade
        extends BaseEntityFacade<RaceParticipationDTO, SaveRaceParticipationDTO, RaceParticipation> {

    List<RaceParticipationDTO> participateInWorldChampionship(WorldChampionshipSetupDTO worldChampionshipSetupDTO);
}
