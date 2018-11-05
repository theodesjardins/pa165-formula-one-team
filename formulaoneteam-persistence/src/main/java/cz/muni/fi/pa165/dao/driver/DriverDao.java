package cz.muni.fi.pa165.dao.driver;

import cz.muni.fi.pa165.dao.base.Dao;
import cz.muni.fi.pa165.entity.Driver;

/**
 * Data Access Object interface for Driver entity.
 *
 * @author mrnda (Michal Mrnuštík)
 */
public interface DriverDao extends Dao<Driver> {
    Driver findByEmail(String email);
}
