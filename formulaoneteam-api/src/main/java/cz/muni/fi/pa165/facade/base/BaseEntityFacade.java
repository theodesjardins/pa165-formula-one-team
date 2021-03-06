package cz.muni.fi.pa165.facade.base;

import cz.muni.fi.pa165.dto.base.BaseDTO;
import cz.muni.fi.pa165.entity.base.BaseEntity;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public interface BaseEntityFacade<DTO extends BaseDTO, SaveDTO extends BaseDTO, E extends BaseEntity>
        extends BaseFacade<DTO, E> {

    long add(SaveDTO dto);

    void remove(long id);

    void update(SaveDTO dto, long id);
}
