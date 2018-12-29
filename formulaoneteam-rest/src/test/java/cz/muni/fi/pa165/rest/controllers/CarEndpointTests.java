package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.carsetup.CarSetupDTO;
import cz.muni.fi.pa165.exceptions.EntityNotFoundException;
import cz.muni.fi.pa165.facade.CarSetupFacade;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CarEndpointTests extends BaseControllerTests<CarEndpoint> {

    @Mock
    private CarSetupFacade facade;

    @InjectMocks
    private CarEndpoint controller;

    @Test
    public void getCars_whenCarsPresent_carsReturned() throws Exception {
        //When
        final List<CarSetupDTO> cars = Arrays.asList(createCarSetupDTO(), createCarSetupDTO());
        when(facade.getAll()).thenReturn(cars);

        //Then
        mockMvc.perform(get("/car/"))
                .andExpect(status().isOk())
                .andExpect(content().json(convertToJson(cars)));
    }

    @Test
    public void getCarById_whenCarExists_carReturned() throws Exception {
        //When
        final CarSetupDTO car = createCarSetupDTO();
        when(facade.findById(car.getId())).thenReturn(car);

        //Then
        mockMvc.perform(get("/car/" + car.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json(convertToJson(car)));
    }

    @Test
    public void getCarById_whenCarDoesNotExits_statusIsNotFound() throws Exception {
        //When
        when(facade.findById(-1)).thenThrow(new EntityNotFoundException(-1, "CarSetup"));

        //Then
        mockMvc.perform(get("/car/-1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createCar_whenCarIsValid_statusIsOk() throws Exception {
        //Given
        final CarSetupDTO car = createCarSetupDTO();

        //Then
        mockMvc.perform(post("/car/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(car)))
                .andExpect(status().isOk());
    }

    @Test
    public void createCar_whenCarIsInvalid_statusIsUnprocessable() throws Exception {
        //Given
        final CarSetupDTO car = createCarSetupDTO();
        car.setBrakes(null);

        //When
        when(facade.add(car)).thenThrow(FormulaOneTeamException.class);

        //Then
        mockMvc.perform(post("/car/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(car)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void updateCar_whenCarIsValid_statusIsOk() throws Exception {
        //Given
        final CarSetupDTO car = createCarSetupDTO();

        //Then
        mockMvc.perform(put("/car/" + car.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(car)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateCar_whenCarIsInvalid_statusIsUnprocessable() throws Exception {
        //Given
        final CarSetupDTO car = createCarSetupDTO();
        car.setBrakes(null);

        //When
        doThrow(FormulaOneTeamException.class).when(facade).update(car, car.getId());

        //Then
        mockMvc.perform(put("/car/" + car.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(car)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void deleteCar_withValidId_statusIsOk() throws Exception {
        //Then
        mockMvc.perform(delete("/car/1"))
                .andExpect(status().isAccepted());
    }

    @Override
    protected CarEndpoint getController() {
        return controller;
    }
}
