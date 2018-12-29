package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.AuthenticateDTO;
import cz.muni.fi.pa165.dto.EngineerDTO;
import cz.muni.fi.pa165.exceptions.EntityNotFoundException;
import cz.muni.fi.pa165.facade.EngineerFacade;
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

public class EngineerControllerTests extends BaseControllerTests<EngineerEndpoint> {
    @InjectMocks
    private EngineerEndpoint controller;

    @Mock
    private EngineerFacade facade;

    @Test
    public void getEngineers_whenEngineersPresent_engineersReturned() throws Exception {
        //When
        final List<EngineerDTO> engineers = Arrays.asList(createEngineerDTO(), createEngineerDTO());
        when(facade.getAll()).thenReturn(engineers);

        //Then
        mockMvc.perform(get("/engineer/"))
                .andExpect(status().isOk())
                .andExpect(content().json(convertToJson(engineers)));
    }

    @Test
    public void getEngineerById_whenEngineerExists_engineerReturned() throws Exception {
        //When
        final EngineerDTO engineer = createEngineerDTO();
        when(facade.findById(engineer.getId())).thenReturn(engineer);

        //Then
        mockMvc.perform(get("/engineer/" + engineer.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json(convertToJson(engineer)));
    }

    @Test
    public void getEngineerById_whenEngineerDoesNotExits_statusIsNotFound() throws Exception {
        //When
        when(facade.findById(-1)).thenThrow(new EntityNotFoundException(-1, "Engineer"));

        //Then
        mockMvc.perform(get("/engineer/-1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createEngineer_whenEngineerIsValid_statusIsOk() throws Exception {
        //Given
        final EngineerDTO engineer = createEngineerDTO();

        //Then
        mockMvc.perform(post("/engineer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(engineer)))
                .andExpect(status().isOk());
    }

    @Test
    public void createEngineer_whenEngineerIsInvalid_statusIsUnprocessable() throws Exception {
        //Given
        final EngineerDTO engineer = createEngineerDTO();

        //When
        doThrow(FormulaOneTeamException.class).when(facade).register(engineer, null);

        //Then
        mockMvc.perform(post("/engineer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(engineer)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void deleteEngineer_withValidDto_statusIsOk() throws Exception {
        //Then
        mockMvc.perform(delete("/engineer/").contentType(MediaType.APPLICATION_JSON)
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
        mockMvc.perform(post("/engineer/auth").contentType(MediaType.APPLICATION_JSON)
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
        mockMvc.perform(post("/engineer/auth").contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(authDto)))
                .andExpect(status().isBadRequest());
    }

    @Override
    protected EngineerEndpoint getController() {
        return controller;
    }

}
