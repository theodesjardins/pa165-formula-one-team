package cz.muni.fi.pa165.facade.base;

import cz.muni.fi.pa165.dto.base.BaseDTO;
import cz.muni.fi.pa165.entity.base.BaseEntity;

/**
 * @author elderanakain (Arcadii Rubailo)
 */
public interface SimpleEntityFacade<DTO extends BaseDTO, E extends BaseEntity> extends BaseEntityFacade<DTO, DTO, E> {
}
