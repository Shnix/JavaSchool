package controller;

import dao.DriverDao;
import dto.DriverDto;
import entity.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.DriverService;

import java.util.List;


@RestController
@CrossOrigin
public class DriverController {


    @Autowired
    DriverService driverService;

    @GetMapping("/drivers")
    public List<DriverDto> hey(){
         return driverService.getAllDrivers();
    }

    @DeleteMapping("drivers/{id}")
    public void delete(@PathVariable String id){
        driverService.deleteDriver(id);
    }
}
