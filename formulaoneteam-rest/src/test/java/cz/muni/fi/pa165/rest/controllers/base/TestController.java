package cz.muni.fi.pa165.rest.controllers.base;

import cz.muni.fi.pa165.facade.base.SimpleEntityFacade;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mrnda (Michal Mrnuštík)
 */
@RestController
class TestController extends EntityController<SimpleEntityFacade<TestDTO, TestEntity>, TestDTO, TestDTO, TestEntity> {
}
