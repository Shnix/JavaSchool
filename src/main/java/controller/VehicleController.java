package controller;


import dto.VehicleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.VehicleService;

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

    @GetMapping("/vehicles")
    public List<VehicleDto> hey(){
        return vehicleService.getAllVehicles();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        vehicleService.deleteVehicle(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody VehicleDto vehicleDto){
        vehicleService.addVehicle(vehicleDto);
    }

    @PostMapping("/update/{id}")
    public void update(@PathVariable int id ,@RequestBody VehicleDto vehicleDto) {
        vehicleService.update(id,vehicleDto);
    }
}
