package controller;

import dao.DriverDao;
import dto.CompleteOrderDto;
import dto.DriverDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import service.DriverService;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DriverControllerTest {

    DriverDto driverDto;

    @Mock
    DriverService driverService;


    @InjectMocks
    DriverController driverController;

    @Before
    public void setup() {
        driverDto = new DriverDto();
        driverDto.setId(999);
        driverDto.setFirstName("Alex");
        driverDto.setLastName("Popov");
        driverDto.setCurrentCity("Moscow");
        driverDto.setDriverType("Pilot");
        driverDto.setWorkingHours(15);
    }

    @Test
    public void listAllDrivers() {
            when(driverService.getAllDrivers()).thenReturn(new ArrayList<DriverDto>());
            driverController.listAllDrivers();
            verify(driverService).getAllDrivers();
    }

    @Test
    public void deleteDriver() {
        driverController.deleteDriver( String.valueOf(driverDto.getId()));
    }

    @Test
    public void addDriver() {

    }

    @Test
    public void updateDriver() {
    }
}