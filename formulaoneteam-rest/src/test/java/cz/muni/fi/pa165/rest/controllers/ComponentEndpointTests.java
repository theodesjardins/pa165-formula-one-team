package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.ComponentDTO;
import cz.muni.fi.pa165.exceptions.EntityNotFoundException;
import cz.muni.fi.pa165.facade.ComponentFacade;
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

public class ComponentEndpointTests extends BaseControllerTests<ComponentEndpoint> {

    @Mock
    private ComponentFacade facade;

    @InjectMocks
    private ComponentEndpoint controller;

    @Test
    public void getComponents_whenComponentsPresent_componentsReturned() throws Exception {
        //When
        final List<ComponentDTO> components = Arrays.asList(createComponentDTO(), createComponentDTO());
        when(facade.getAll()).thenReturn(components);

        //Then
        mockMvc.perform(get("/component/"))
                .andExpect(status().isOk())
                .andExpect(content().json(convertToJson(components)));
    }

    @Test
    public void getComponentById_whenComponentExists_componentReturned() throws Exception {
        //When
        final ComponentDTO component = createComponentDTO();
        when(facade.findById(component.getId())).thenReturn(component);

        //Then
        mockMvc.perform(get("/component/" + component.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json(convertToJson(component)));
    }

    @Test
    public void getComponentById_whenComponentDoesNotExits_statusIsNotFound() throws Exception {
        //When
        when(facade.findById(-1)).thenThrow(new EntityNotFoundException(-1, "Component"));

        //Then
        mockMvc.perform(get("/component/-1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createComponent_whenComponentIsValid_statusIsOk() throws Exception {
        //Given
        final ComponentDTO component = createComponentDTO();

        //Then
        mockMvc.perform(post("/component/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(component)))
                .andExpect(status().isOk());
    }

    @Test
    public void createComponent_whenComponentIsInvalid_statusIsUnprocessable() throws Exception {
        //Given
        final ComponentDTO component = createComponentDTO();
        component.setName(null);

        //When
        when(facade.add(component)).thenThrow(FormulaOneTeamException.class);

        //Then
        mockMvc.perform(post("/component/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(component)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void updateComponent_whenComponentIsValid_statusIsOk() throws Exception {
        //Given
        final ComponentDTO component = createComponentDTO();

        //Then
        mockMvc.perform(put("/component/" + component.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(component)))
                .andExpect(status().isOk());
    }

    @Test
    public void updateComponent_whenComponentIsInvalid_statusIsUnprocessable() throws Exception {
        //Given
        final ComponentDTO component = createComponentDTO();
        component.setName(null);

        //When
        doThrow(FormulaOneTeamException.class).when(facade).update(component, component.getId());

        //Then
        mockMvc.perform(put("/component/" + component.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertToJson(component)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void deleteComponent_withValidId_statusIsOk() throws Exception {
        //Then
        mockMvc.perform(delete("/component/1"))
                .andExpect(status().isAccepted());
    }

    @Override
    protected ComponentEndpoint getController() {
        return controller;
    }
}
