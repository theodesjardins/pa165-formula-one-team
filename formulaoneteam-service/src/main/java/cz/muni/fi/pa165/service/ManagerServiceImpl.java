package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.manager.ManagerDao;
import cz.muni.fi.pa165.entity.Manager;
import cz.muni.fi.pa165.service.base.BaseUserServiceImpl;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import org.springframework.stereotype.Service;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
@Service
public class ManagerServiceImpl extends BaseUserServiceImpl<Manager, ManagerDao> implements ManagerService {

    @Override
    protected Class<Manager> getEntityClass() {
        return Manager.class;
    }
}
