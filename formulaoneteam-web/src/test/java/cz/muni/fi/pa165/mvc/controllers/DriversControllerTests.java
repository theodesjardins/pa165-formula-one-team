package cz.muni.fi.pa165.mvc.controllers;

import cz.muni.fi.pa165.dto.driver.DriverDTO;
import cz.muni.fi.pa165.enums.DriverStatus;
import cz.muni.fi.pa165.facade.DriverFacade;
import cz.muni.fi.pa165.mvc.config.security.AuthenticationFacade;
import cz.muni.fi.pa165.mvc.config.security.SecurityRole;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class DriversControllerTests extends BaseControllerTest<DriversController> {

    @Mock
    private AuthenticationFacade authenticationFacade;

    @Mock
    private DriverFacade driverFacadeMock;

    @InjectMocks
    private DriversController controller;

    @Test
    public void list_statusIsOk() throws Exception {
        //Then
        mockMvc.perform(get("/drivers/list"))
                .andExpect(status().isOk());
    }

    @Test
    public void list_whenDriversArePresent_attributeEquals() throws Exception {
        //When
        List<DriverDTO> drivers = Arrays.asList(createDriverDTO(), createDriverDTO());
        when(driverFacadeMock.getAll()).thenReturn(drivers);

        //Then
        mockMvc.perform(get("/drivers/list"))
                .andExpect(model().attributeExists("drivers"))
                .andExpect(model().attribute("drivers", drivers));
    }

    @Test
    public void detail_whenDriverExists_statusIsOk() throws Exception {
        //When
        when(driverFacadeMock.findById(1)).thenReturn(createDriverDTO());

        //Then
        mockMvc.perform(get("/drivers/detail/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void detail_whenDriverExists_addsModelAttribute() throws Exception {
        //When
        final DriverDTO driverDTO = createDriverDTO();
        when(driverFacadeMock.findById(1)).thenReturn(driverDTO);

        //Then
        mockMvc.perform(get("/drivers/detail/1"))
                .andExpect(model().attributeExists("driver"))
                .andExpect(model().attribute("driver", driverDTO));
    }

    @Test
    public void create_withoutManagerLoggedIn_throwsAccessDenied() throws Exception {
        when(authenticationFacade.hasRole(SecurityRole.MANAGER)).thenReturn(false);

        //Then
        mockMvc.perform(get("/drivers/create"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/forbidden?message=Only manager can create new users.&driverStatusValues=Main&driverStatusValues=Test&lang=en"))
                .andReturn();
    }

    @Test
    public void create_withManagerLoggedIn_statusIsOk() throws Exception {
        when(authenticationFacade.hasRole(SecurityRole.MANAGER)).thenReturn(true);

        //Then
        mockMvc.perform(get("/drivers/create"))
                .andExpect(status().isOk());
    }

    @Test
    public void create_withManagerLoggedIn_createsDefaultDriver() throws Exception {
        //When
        final DriverDTO driverDTO = createDriverDTO();
        when(authenticationFacade.hasRole(SecurityRole.MANAGER)).thenReturn(true);
        when(driverFacadeMock.createDefaultDriver()).thenReturn(driverDTO);

        //Then
        mockMvc.perform(get("/drivers/create"))
                .andExpect(model().attributeExists("driver"))
                .andExpect(model().attribute("driver", driverDTO));
    }

    @Test
    public void edit_withExistingDriverAndWithoutLogin_throwsAccessDenied() throws Exception {
        //When
        final DriverDTO driverDTO = createDriverDTO();
        when(driverFacadeMock.findById(1)).thenReturn(driverDTO);
        when(authenticationFacade.isAuthenticated()).thenReturn(false);

        //Then
        mockMvc.perform(get("/drivers/edit/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/forbidden?message=You can't edit this user!&driverStatusValues=Main&driverStatusValues=Test&lang=en"))
                .andReturn();
    }

    @Test
    public void edit_withExistingDriverAndLoggedInManager_statusIsOk() throws Exception {
        //When
        final DriverDTO driverDTO = createDriverDTO();
        when(driverFacadeMock.findById(1)).thenReturn(driverDTO);
        when(authenticationFacade.isAuthenticated()).thenReturn(true);
        when(authenticationFacade.hasRole(SecurityRole.MANAGER)).thenReturn(true);

        //Then
        mockMvc.perform(get("/drivers/edit/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void edit_withExistingAndLoggedInDriver_addsDriverAttribute() throws Exception {
        //When
        final DriverDTO driverDTO = createDriverDTO();
        when(driverFacadeMock.findById(1)).thenReturn(driverDTO);
        when(authenticationFacade.isAuthenticated()).thenReturn(true);
        when(authenticationFacade.getCurrentUserEmail()).thenReturn(driverDTO.getEmail());

        //Then
        mockMvc.perform(get("/drivers/edit/1"))
                .andExpect(model().attributeExists("driver"))
                .andExpect(model().attribute("driver", driverDTO));
    }

    @Test
    public void edit_withExistingDriverAndLoggedInDriver_statusIsOk() throws Exception {
        //When
        final DriverDTO driverDTO = createDriverDTO();
        when(driverFacadeMock.findById(1)).thenReturn(driverDTO);
        when(authenticationFacade.isAuthenticated()).thenReturn(true);
        when(authenticationFacade.getCurrentUserEmail()).thenReturn(driverDTO.getEmail());

        //Then
        mockMvc.perform(get("/drivers/edit/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void submit_withValidExistingDriver_statusRedirect() throws Exception {
        //Then
        final DriverDTO driverDTO = createDriverDTO();
        driverDTO.setId(1);
        mockMvc.perform(post("/drivers/submit").flashAttr("driver", driverDTO))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void submit_withValidExistingDriver_driverUpdated() throws Exception {
        //Then
        final DriverDTO driverDTO = createDriverDTO();
        driverDTO.setId(1);
        mockMvc.perform(post("/drivers/submit").flashAttr("driver", driverDTO));
        verify(driverFacadeMock).updateDriver(driverDTO);
    }

    @Test
    public void submit_withValidDriver_statusRedirect() throws Exception {
        //Then
        final DriverDTO driverDTO = createDriverDTO();
        mockMvc.perform(post("/drivers/submit").flashAttr("driver", driverDTO))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void submit_withValidDriver_driverRegistered() throws Exception {
        //Then
        final DriverDTO driverDTO = createDriverDTO();
        mockMvc.perform(post("/drivers/submit").flashAttr("driver", driverDTO));
        verify(driverFacadeMock).register(driverDTO, driverDTO.getPassword());
    }

    @Test
    public void submit_withMissingNameDriver_addsError() throws Exception {
        //Then
        final DriverDTO driverDTO = createDriverDTO();
        driverDTO.setName("");
        mockMvc.perform(post("/drivers/submit").flashAttr("driver", driverDTO))
                .andExpect(model().attributeHasFieldErrors("driver", "name"));
    }

    @Test
    public void submit_withNotSamePasswordDriver_addsError() throws Exception {
        //Then
        final DriverDTO driverDTO = createDriverDTO();
        driverDTO.setConfirmPassword("");
        mockMvc.perform(post("/drivers/submit").flashAttr("driver", driverDTO))
                .andExpect(model().attributeHasFieldErrors("driver", "password", "confirmPassword"));
    }

    @Test
    public void submit_withNewDriverAndMissingPassword_addsError() throws Exception {
        //Then
        final DriverDTO driverDTO = createDriverDTO();
        driverDTO.setPassword("");
        driverDTO.setConfirmPassword("");
        mockMvc.perform(post("/drivers/submit").flashAttr("driver", driverDTO))
                .andExpect(model().attributeHasFieldErrors("driver", "password"));
    }

    private DriverDTO createDriverDTO() {
        DriverDTO driverDto = new DriverDTO();
        driverDto.setName("John");
        driverDto.setSurname("Doe");
        driverDto.setEmail("john@doe.com");
        driverDto.setNationality("American");
        driverDto.setBirthdayString("02/09/1989");
        driverDto.setBirthday(createDate(2, 9, 1989));
        driverDto.setPassword("password");
        driverDto.setConfirmPassword("password");
        driverDto.setDriverStatus(DriverStatus.MAIN);
        return driverDto;
    }

    private Date createDate(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    @Override
    protected DriversController getController() {
        return controller;
    }
}
