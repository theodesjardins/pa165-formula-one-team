package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.facade.RaceParticipationFacade;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import cz.muni.fi.pa165.facade.CarSetupFacade;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CarsControllerTests extends BaseControllerTest<CarsController> {

    @Mock 
    private CarSetupFacade carFacade;
    @Mock
    private RaceParticipationFacade raceParticipationFacade;

    @InjectMocks
    private CarsController controller;

    @Test
    public void list_statusIsOk() throws Exception {
        //Then
        mockMvc.perform(get("/cars/list"))
                .andExpect(status().isOk());
    }

    @Override
    protected CarsController getController() {
        return controller;
    }
}
