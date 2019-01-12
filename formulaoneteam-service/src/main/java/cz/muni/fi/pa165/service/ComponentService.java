package cz.muni.fi.pa165.service;
import java.util.List;

import cz.muni.fi.pa165.entity.Component;
import cz.muni.fi.pa165.enums.ComponentType;
import cz.muni.fi.pa165.service.base.BaseEntityService;

/**
 * @author Théo Desjardins
 */
public interface ComponentService extends BaseEntityService<Component> {
    List<Component> getComponentsOfType(ComponentType type);
}
