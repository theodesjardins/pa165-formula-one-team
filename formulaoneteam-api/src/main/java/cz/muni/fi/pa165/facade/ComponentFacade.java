package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.ComponentDTO;
import cz.muni.fi.pa165.dto.ComponentParameterDTO;
import cz.muni.fi.pa165.entity.Component;
import cz.muni.fi.pa165.facade.base.SimpleEntityFacade;

/**
 * @author Théo Desjardins
 */
public interface ComponentFacade extends SimpleEntityFacade<ComponentDTO, Component> {

    void addParameter(long componentId, ComponentParameterDTO parameter);

    void updateParameter(long id, ComponentParameterDTO parameterDTO);

    void removeParameter(long componentId, long parameter);
}
