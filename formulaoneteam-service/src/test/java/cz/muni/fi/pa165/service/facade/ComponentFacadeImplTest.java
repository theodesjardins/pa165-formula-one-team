package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.ComponentDTO;
import cz.muni.fi.pa165.dto.ComponentParameterDTO;
import cz.muni.fi.pa165.entity.CarSetup;
import cz.muni.fi.pa165.entity.Component;
import cz.muni.fi.pa165.entity.ComponentParameter;
import cz.muni.fi.pa165.service.CarSetupService;
import cz.muni.fi.pa165.service.ComponentParameterService;
import cz.muni.fi.pa165.service.ComponentService;
import cz.muni.fi.pa165.service.base.BaseFacadeTest;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.testng.AssertJUnit.*;

/**
 * @author Théo Desjardins
 */
public class ComponentFacadeImplTest extends BaseFacadeTest<Component, ComponentDTO> {

    @Mock
    private ComponentService service;

    @Mock
    private ComponentParameterService componentParameterService;

    @Mock
    private CarSetupService carSetupService;

    @InjectMocks
    private ComponentFacadeImpl componentFacade;

    @Override
    public void setUp() {
        super.setUp();
        ReflectionTestUtils.setField(componentFacade, "service", service);

        when(beanMappingServiceMock.mapTo(dto, Component.class)).thenReturn(entity);
    }

    @Test
    public void findComponentById() {
        //Given
        when(service.findById(entity.getId())).thenReturn(entity);
        when(beanMappingServiceMock.mapTo(entity, ComponentDTO.class)).thenReturn(dto);

        //When
        ComponentDTO resComponentDTO = componentFacade.findById(entity.getId());

        //Then
        assertEquals(resComponentDTO, dto);
    }

    @Test
    public void deleteComponentTest() {

        //when
        componentFacade.remove(dto.getId());

        //then
        verify(service, times(1)).remove(entity.getId());
    }

    @Test(expected = FormulaOneTeamException.class)
    public void deleteComponent_whenComponentIsPartOfCarSetup_throws() {
        //given
        final CarSetup carSetupMock = mock(CarSetup.class);
        when(carSetupMock.getComponents()).thenReturn(Collections.singletonList(entity));
        when(carSetupService.getAll()).thenReturn(Collections.singletonList(carSetupMock));
        when(service.findById(entity.getId())).thenReturn(entity);

        //when
        componentFacade.remove(dto.getId());

        //then
    }

    @Test
    public void updateComponentTest() {

        //when
        componentFacade.update(dto, 1);

        //then
        verify(service, times(1)).update(entity);
    }

    @Test
    public void addComponentTest() {
        //when
        when(service.add(entity)).thenReturn(entity);

        componentFacade.add(dto);

        //then
        verify(service, times(1)).add(entity);
    }

    @Test
    public void getAllComponentTest() {

        //Given
        List<Component> listComponent = new ArrayList<>();
        listComponent.add(entity);
        when(service.getAll()).thenReturn(listComponent);
        List<ComponentDTO> listDTOComponent = new ArrayList<>();
        listDTOComponent.add(dto);
        when(beanMappingServiceMock.mapTo(listComponent, ComponentDTO.class)).thenReturn(listDTOComponent);

        //When
        List<ComponentDTO> resListComponentDTO = new ArrayList<>(componentFacade.getAll());

        //Then
        verify(service).getAll();
        assertEquals(resListComponentDTO.size(), 1);
        Assert.assertTrue(resListComponentDTO.contains(dto));
    }

    @Test
    public void addParameter_withValidParameter_parameterIsAdded() {
        //Given
        ComponentParameter componentParameter = createComponentParameter();
        ComponentParameterDTO componentParameterDTO = createComponentParameterDTO();
        when(beanMappingServiceMock.mapTo(componentParameterDTO, ComponentParameter.class)).thenReturn(componentParameter);
        when(componentParameterService.add(componentParameter)).thenReturn(componentParameter);
        when(service.findById(entity.getId())).thenReturn(entity);

        //When
        componentFacade.addParameter(entity.getId(), componentParameterDTO);

        //Then
        verify(componentParameterService).add(componentParameter);
        verify(service).update(entity);
        assertTrue(entity.getParameters().size() > 0);
    }

    @Test(expected = FormulaOneTeamException.class)
    public void addParameter_withAlreadyContainedParameter_exceptionIsThrown() {
        //Given
        ComponentParameter componentParameter = createComponentParameter();
        ComponentParameterDTO componentParameterDTO = createComponentParameterDTO();
        when(beanMappingServiceMock.mapTo(componentParameterDTO, ComponentParameter.class)).thenReturn(componentParameter);
        when(componentParameterService.add(componentParameter)).thenReturn(componentParameter);
        entity.addParameter(componentParameter);
        when(service.findById(entity.getId())).thenReturn(entity);

        //When
        componentFacade.addParameter(entity.getId(), componentParameterDTO);

        //Then
        fail("Exception should have been thrown.");
    }

    @Test
    public void removeParameter_withValidParameter_parameterIsRemoved() {
        //Given
        ComponentParameter componentParameter = createComponentParameter();
        ComponentParameterDTO componentParameterDTO = createComponentParameterDTO();
        entity.addParameter(componentParameter);
        when(componentParameterService.findById(componentParameterDTO.getId())).thenReturn(componentParameter);
        when(service.findById(entity.getId())).thenReturn(entity);

        //When
        componentFacade.removeParameter(entity.getId(), componentParameterDTO.getId());

        //Then
        verify(componentParameterService).remove(componentParameterDTO.getId());
        verify(service).update(entity);
        assertEquals(0, entity.getParameters().size());
    }


    @Test
    public void updateParameter_withValidParameter_parameterIsUpdated() {
        //Given
        ComponentParameter componentParameter = createComponentParameter();
        ComponentParameterDTO componentParameterDTO = createComponentParameterDTO();
        entity.addParameter(componentParameter);
        when(beanMappingServiceMock.mapTo(componentParameterDTO, ComponentParameter.class)).thenReturn(componentParameter);
        when(componentParameterService.update(componentParameter)).thenReturn(componentParameter);

        //When
        componentFacade.updateParameter(componentParameter.getId(), componentParameterDTO);

        //Then
        verify(componentParameterService).update(componentParameter);
    }

    @Override
    protected Component createTestEntity() {
        return createComponent();
    }

    @Override
    protected ComponentDTO createTestDTO() {
        return createComponentDTO();
    }
}
