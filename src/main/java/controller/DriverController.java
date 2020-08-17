package controller;

import dto.DriverDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.DriverService;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/drivers")
public class DriverController {

    private DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("")
    public List<DriverDto> listAllDrivers() {
        return driverService.getAllDrivers();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        driverService.deleteDriver(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody @Valid DriverDto driverDto) {
        driverService.addDriver(driverDto);
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable String id, @RequestBody @Valid DriverDto driverDto) {
        driverService.update(id, driverDto);
    }

}
