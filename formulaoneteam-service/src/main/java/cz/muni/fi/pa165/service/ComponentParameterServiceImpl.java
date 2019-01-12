package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.componentparameter.ComponentParameterDao;
import cz.muni.fi.pa165.entity.ComponentParameter;
import cz.muni.fi.pa165.service.base.BaseEntityServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Ivan Dendis
 */
@Service
public class ComponentParameterServiceImpl
        extends BaseEntityServiceImpl<ComponentParameter, ComponentParameterDao>
        implements ComponentParameterService {

    @Override
    protected Class<ComponentParameter> getEntityClass() {
        return ComponentParameter.class;
    }
}
