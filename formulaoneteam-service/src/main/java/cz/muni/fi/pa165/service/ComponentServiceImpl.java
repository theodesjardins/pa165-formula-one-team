package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.component.ComponentDao;
import cz.muni.fi.pa165.entity.Component;
import cz.muni.fi.pa165.enums.ComponentType;
import cz.muni.fi.pa165.service.base.BaseEntityServiceImpl;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

/**
 * @author Théo Desjardins
 */
@Service
public class ComponentServiceImpl extends BaseEntityServiceImpl<Component, ComponentDao> implements ComponentService {

    @Override
    protected Class<Component> getEntityClass() {
        return Component.class;
    }
    
    @Override
    public List<Component> getComponentsOfType(ComponentType type) {
        List<Component> allComponents = dao.findAll();

        return allComponents.stream()
            .filter(c -> c.getType() == type)
            .collect(Collectors.toList());
    }
}
