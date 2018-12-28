package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.race.RaceDTO;
import cz.muni.fi.pa165.entity.Race;
import cz.muni.fi.pa165.facade.RaceFacade;
import cz.muni.fi.pa165.rest.ApiUris;
import cz.muni.fi.pa165.rest.controllers.base.EntityController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Adel Chakouri
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_RACE)
class RaceController extends EntityController<RaceFacade, RaceDTO, RaceDTO, Race> {
}
