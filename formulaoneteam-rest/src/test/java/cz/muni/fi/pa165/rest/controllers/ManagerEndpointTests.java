package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.AuthenticateDTO;
import cz.muni.fi.pa165.dto.ManagerDTO;
import cz.muni.fi.pa165.exceptions.EntityNotFoundException;
import cz.muni.fi.pa165.facade.ManagerFacade;
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

public class ManagerEndpointTests extends BaseControllerTests<ManagerEndpoint> {
    @InjectMocks
    private ManagerEndpoint controller;

    @Mock
    private ManagerFacade facade;

    @Test
    public void getManagers_whenManagersPresent_managersReturned() throws Exception {
        //When
        final List<ManagerDTO> managers = Arrays.asList(createManagerDTO(), createManagerDTO());
        when(facade.getAll()).thenReturn(managers);

        //Then
        mockMvc.perform(get("/manager/"))
                .andExpect(status().isOk())
                .andExpect(content().json(convertToJson(managers)));
    }

    @Test
    public void getManagerById_whenManagerExists_managerReturned() throws Exception {
        //When
        final ManagerDTO manager = createManagerDTO();
        when(facade.findById(manager.getId())).thenReturn(manager);

        //Then
        mockMvc.perform(get("/manager/" + manager.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json(convertToJson(manager)));
    }

    @Test
    public void getManagerById_whenManagerDoesNotExits_statusIsNotFound() throws Exception {
        //When
        when(facade.findById(-1)).thenThrow(new EntityNotFoundException(-1, "Manager"));

        //Then
        mockMvc.perform(get("/manager/-1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createManager_whenManagerIsValid_statusIsOk() throws Exception {
        //Given
        final ManagerDTO manager = createManagerDTO();

        //Then
        mockMvc.perform(post("/manager/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(manager)))
                .andExpect(status().isOk());
    }

    @Test
    public void createManager_whenManagerIsInvalid_statusIsUnprocessable() throws Exception {
        //Given
        final ManagerDTO manager = createManagerDTO();

        //When
        doThrow(FormulaOneTeamException.class).when(facade).register(manager, null);

        //Then
        mockMvc.perform(post("/manager/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(manager)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void deleteManager_withValidDto_statusIsOk() throws Exception {
        //Then
        mockMvc.perform(delete("/manager/").contentType(MediaType.APPLICATION_JSON)
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
        mockMvc.perform(post("/manager/auth").contentType(MediaType.APPLICATION_JSON)
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
        mockMvc.perform(post("/manager/auth").contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(authDto)))
                .andExpect(status().isBadRequest());
    }

    @Override
    protected ManagerEndpoint getController() {
        return controller;
    }

}
