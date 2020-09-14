package controller;


import dto.VehicleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.VehicleService;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/vehicles")
public class VehicleController {

    private VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/")
    public List<VehicleDto> listAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @DeleteMapping("/{id}")
    public void deleteVehicle(@PathVariable String id) {
        vehicleService.deleteVehicle(id);
    }

    @PostMapping("/add")
    public void addVehicle(@RequestBody @Valid VehicleDto vehicleDto) {
        vehicleService.addVehicle(vehicleDto);
    }

    @PutMapping("/update/{id}")
    public void updateVehicle(@PathVariable String id, @RequestBody @Valid VehicleDto vehicleDto) {
        vehicleService.update(id, vehicleDto);
    }
}
