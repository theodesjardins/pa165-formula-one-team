package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.dao.Race.RaceDao;
import cz.muni.fi.pa165.entity.Race;
import cz.muni.fi.pa165.service.base.BaseEntityServiceImpl;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import org.springframework.stereotype.Service;

/**
 * @author Adel Chakouri
 */
@Service
public class RaceServiceImpl extends BaseEntityServiceImpl<Race, RaceDao> implements RaceService {

    @Override
    protected Class<Race> getEntityClass() {
        return Race.class;
    }
}
