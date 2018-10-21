package cz.muni.fi.pa165.dao;

import cz.muni.fi.pa165.entity.Manager;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author elderanakain (Arcadii Rubailo)
 * Data Access Object interface for Manager entity with basic CRUD operations.
 */
@Repository
public interface ManagerDao {

    Manager findById(Long id);

    void add(Manager manager);

    void delete(Manager manager);

    void update(Manager manager);

    List<Manager> findAll();

    Manager findByName(String name);
}
