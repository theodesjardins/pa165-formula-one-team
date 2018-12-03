package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.ComponentDTO;
import cz.muni.fi.pa165.entity.Component;
import cz.muni.fi.pa165.enums.ComponentType;
import cz.muni.fi.pa165.service.ComponentService;
import cz.muni.fi.pa165.service.base.BaseFacadeTest;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Théo Desjardins
 */

public class ComponentFacadeImplTest extends BaseFacadeTest<Component, ComponentDTO> {

    @Mock
    private ComponentService componentService;

    @InjectMocks
    private ComponentFacadeImpl componentFacade;

    @Override
    public void setUp() {
        super.setUp();

        when(beanMappingServiceMock.mapTo(dto, Component.class)).thenReturn(entity);
    }

    @Test
    public void findComponentById() {
        //Given
        when(componentService.findById(entity.getId())).thenReturn(entity);
        when(beanMappingServiceMock.mapTo(entity, ComponentDTO.class)).thenReturn(dto);

        //When
        ComponentDTO resComponentDTO = componentFacade.findById(entity.getId());

        //Then
        assertEquals(resComponentDTO, dto);
    }

    @Test
    public void deleteComponentTest() {

        //when
        componentFacade.remove(dto);

        //then
        verify(componentService, times(1)).remove(entity);
    }

    @Test
    public void updateComponentTest() {

        //when
        componentFacade.update(dto);

        //then
        verify(componentService, times(1)).update(entity);
    }

    @Test
    public void addComponentTest() {

        //when
        componentFacade.add(dto);

        //then
        verify(componentService, times(1)).add(entity);
    }

    @Test
    public void getAllComponentTest() {

        //Given
        List<Component> listComponent = new ArrayList<>();
        listComponent.add(entity);
        when(componentService.getAll()).thenReturn(listComponent);
        List<ComponentDTO> listDTOComponent = new ArrayList<>();
        listDTOComponent.add(dto);
        when(beanMappingServiceMock.mapTo(listComponent, ComponentDTO.class)).thenReturn(listDTOComponent);

        //When
        List<ComponentDTO> resListComponentDTO = new ArrayList<>(componentFacade.getAll());

        //Then
        verify(componentService).getAll();
        assertEquals(resListComponentDTO.size(), 1);
        Assert.assertTrue(resListComponentDTO.contains(dto));
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
