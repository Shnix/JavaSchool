package controller;

import dto.DriverDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.DriverService;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/drivers")
public class DriverController {

    DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService=driverService;
    }

    @GetMapping("")
    public List<DriverDto> hey(){
        return driverService.getAllDrivers();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        driverService.deleteDriver(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody DriverDto driverDto){
        driverService.addDriver(driverDto);
    }

    @PostMapping("/update/{id}")
    public void update(@PathVariable int id ,@RequestBody DriverDto driverDto){
        driverService.update(id,driverDto);
    }

}
