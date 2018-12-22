package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.AuthenticateDTO;
import cz.muni.fi.pa165.dto.driver.DriverDTO;
import cz.muni.fi.pa165.exceptions.EntityNotFoundException;
import cz.muni.fi.pa165.facade.DriverFacade;
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

public class DriverControllerTests extends BaseControllerTests<DriverController> {
    @InjectMocks
    private DriverController controller;

    @Mock
    private DriverFacade facade;

    @Test
    public void getDrivers_whenDriversPresent_driversReturned() throws Exception {
        //When
        final List<DriverDTO> drivers = Arrays.asList(createDriverDTO(), createDriverDTO());
        when(facade.getAll()).thenReturn(drivers);

        //Then
        mockMvc.perform(get("/driver/"))
                .andExpect(status().isOk())
                .andExpect(content().json(convertToJson(drivers)));
    }

    @Test
    public void getDriverById_whenDriverExists_driverReturned() throws Exception {
        //When
        final DriverDTO driver = createDriverDTO();
        when(facade.findById(driver.getId())).thenReturn(driver);

        //Then
        mockMvc.perform(get("/driver/" + driver.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json(convertToJson(driver)));
    }

    @Test
    public void getDriverById_whenDriverDoesNotExits_statusIsNotFound() throws Exception {
        //When
        when(facade.findById(-1)).thenThrow(new EntityNotFoundException(-1, "Driver"));

        //Then
        mockMvc.perform(get("/driver/-1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createDriver_whenDriverIsValid_statusIsOk() throws Exception {
        //Given
        final DriverDTO driver = createDriverDTO();

        //Then
        mockMvc.perform(post("/driver/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(driver)))
                .andExpect(status().isOk());
    }

    @Test
    public void createDriver_whenDriverIsInvalid_statusIsUnprocessable() throws Exception {
        //Given
        final DriverDTO driver = createDriverDTO();

        //When
        doThrow(FormulaOneTeamException.class).when(facade).register(driver, null);

        //Then
        mockMvc.perform(post("/driver/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(driver)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void deleteDriver_withValidDto_statusIsOk() throws Exception {
        //Then
        mockMvc.perform(delete("/driver/").contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(createAuthenticateDTO())))
                .andExpect(status().isOk());
    }

    @Test
    public void authenticate_withValidDto_statusIsOk() throws Exception {
        //Given
        final AuthenticateDTO authDto = createAuthenticateDTO();

        //When
        when(facade.authenticate(authDto)).thenReturn(true);

        //Then
        mockMvc.perform(post("/driver/auth").contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(authDto)))
                .andExpect(status().isOk());
    }

    @Test
    public void authenticate_withInvalidDto_statusIsBadRequest() throws Exception {
        //Given
        final AuthenticateDTO authDto = createAuthenticateDTO();

        //When
        when(facade.authenticate(authDto)).thenReturn(false);

        //Then
        mockMvc.perform(post("/driver/auth").contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(authDto)))
                .andExpect(status().isBadRequest());
    }

    @Override
    protected DriverController getController() {
        return controller;
    }

}
