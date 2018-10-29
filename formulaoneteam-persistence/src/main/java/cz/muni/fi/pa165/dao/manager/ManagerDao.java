package cz.muni.fi.pa165.dao.manager;

import cz.muni.fi.pa165.dao.base.Dao;
import cz.muni.fi.pa165.entity.Manager;

/**
 * @author elderanakain (Arcadii Rubailo)
 * Data Access Object interface for Manager entity with basic CRUD operations.
 */
public interface ManagerDao extends Dao<Manager> {
    Manager findByEmail(String email);
}
