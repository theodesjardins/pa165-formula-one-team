package cz.muni.fi.pa165.rest.controllers;

import cz.muni.fi.pa165.dto.testdrive.SaveTestDriveDTO;
import cz.muni.fi.pa165.dto.testdrive.TestDriveDTO;
import cz.muni.fi.pa165.exceptions.EntityNotFoundException;
import cz.muni.fi.pa165.facade.TestDriveFacade;
import cz.muni.fi.pa165.service.exceptions.FormulaOneTeamException;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Adel Chakouri
 */
public class TestDriveControllerTest extends BaseControllerTests<TestDriveEndpoint> {

    @Mock
    private TestDriveFacade testDriveFacade;

    @InjectMocks
    private TestDriveEndpoint testDriveEndpoint;

    @Test
    public void getValidTestDriveTest() throws Exception {
        //given
        TestDriveDTO testDriveDTO = createTestDriveDTO();
        String json = convertToJson(testDriveDTO);

        //when
        when(testDriveFacade.findById(testDriveDTO.getId())).thenReturn(testDriveDTO);

        //then
        mockMvc.perform(get("/test-drive/22"))
                .andExpect(status().isOk())
                .andExpect(content().json(json));
    }

    @Test
    public void getInvalidTestDriveTest() throws Exception {
        //Given
        when(testDriveFacade.findById(1)).thenThrow(EntityNotFoundException.class);

        //Then
        mockMvc.perform(get("/test-drive/1")).andExpect(status().isNotFound());
    }

    @Test
    public void getTestDrive() throws Exception {
        //given
        List<TestDriveDTO> testDrives = Arrays.asList(createTestDriveDTO(), createTestDriveDTO());
        String json = convertToJson(testDrives);

        //when
        when(testDriveFacade.getAll()).thenReturn(testDrives);

        //then
        mockMvc.perform(get("/test-drive/")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json(json));
    }

    @Test
    public void deleteTestDrive() throws Exception {

        //Then
        mockMvc.perform(delete("/test-drive/1"))
                .andExpect(status().isAccepted());
    }

    @Test
    public void deleteTestDriveNonExisting() throws Exception {
        //given
        doThrow(EntityNotFoundException.class).when(testDriveFacade).remove(1);

        //then
        mockMvc.perform(delete("/test-drive/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addTestDrive_returnsOk() throws Exception {
        //given
        SaveTestDriveDTO saveTestDrive = createSaveTestDriveDTO();
        TestDriveDTO testDrive = createTestDriveDTO();

        //when
        when(testDriveFacade.add(saveTestDrive)).thenReturn(testDrive.getId());
        when(testDriveFacade.findById(testDrive.getId())).thenReturn(testDrive);

        //Then
        mockMvc.perform(post("/test-drive/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(convertToJson(saveTestDrive)))
                .andExpect(status().isOk())
                .andExpect(content().json(convertToJson(testDrive)));
    }

    @Test
    public void addTestDrive_returnsException() throws Exception {
        //Given
        SaveTestDriveDTO testDriveDTO = new SaveTestDriveDTO();
        testDriveDTO.setId(-1);

        //when
        when(testDriveFacade.add(testDriveDTO)).thenThrow(FormulaOneTeamException.class);
        String json = convertToJson(testDriveDTO);

        //Then
        mockMvc.perform(post("/test-drive/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void updateTestDrive_returnsException() throws Exception {
        //Given
        SaveTestDriveDTO testDriveDTO = new SaveTestDriveDTO();
        testDriveDTO.setId(0);
        String json = convertToJson(testDriveDTO);
        doThrow(FormulaOneTeamException.class).when(testDriveFacade).update(testDriveDTO, 0);

        //Then
        mockMvc.perform(put("/test-drive/0")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isUnprocessableEntity());
    }

    @Override
    protected TestDriveEndpoint getController() {
        return testDriveEndpoint;
    }
}
