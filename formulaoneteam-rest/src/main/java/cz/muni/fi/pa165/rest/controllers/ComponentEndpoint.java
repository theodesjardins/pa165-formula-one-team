package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.ComponentDTO;
import cz.muni.fi.pa165.dto.ComponentParameterDTO;
import cz.muni.fi.pa165.entity.Component;
import cz.muni.fi.pa165.facade.ComponentFacade;
import cz.muni.fi.pa165.rest.ApiUris;
import cz.muni.fi.pa165.rest.controllers.base.EntityController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_COMPONENT)
class ComponentEndpoint extends EntityController<ComponentFacade, ComponentDTO, ComponentDTO, Component> {

    @PostMapping(value = "/parameters/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ComponentDTO> addParameter(
            @PathVariable long id,
            @RequestBody ComponentParameterDTO parameter
    ) {
        facade.addParameter(id, parameter);
        return ok(facade.findById(id));
    }

    @PutMapping(value = "/parameters/{parameterId}", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity updateParameter(
            @PathVariable long parameterId,
            @RequestBody ComponentParameterDTO parameter
    ) {
        facade.updateParameter(parameterId, parameter);
        return ok();
    }

    @DeleteMapping("/parameters/{componentId}/{parameterId}")
    public ResponseEntity<ComponentDTO> deleteParameter(
            @PathVariable long componentId,
            @PathVariable long parameterId
    ) {
        facade.removeParameter(componentId, parameterId);
        return ok(facade.findById(componentId));
    }
}
