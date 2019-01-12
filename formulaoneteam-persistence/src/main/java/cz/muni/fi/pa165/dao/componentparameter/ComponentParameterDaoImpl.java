package cz.muni.fi.pa165.dao.componentparameter;

import cz.muni.fi.pa165.dao.base.DaoImpl;
import cz.muni.fi.pa165.entity.ComponentParameter;
import org.springframework.stereotype.Repository;

/**
 * @author Ivan Dendis
 */
@Repository
public class ComponentParameterDaoImpl extends DaoImpl<ComponentParameter> implements ComponentParameterDao {

    @Override
    protected Class<ComponentParameter> getClassType() {
        return ComponentParameter.class;
    }
}
