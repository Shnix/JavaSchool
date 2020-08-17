package controller;


import dto.DriverInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.CargoService;
import service.DriverInfoService;
import service.DriverService;
import service.OrderService;

@RestController
@CrossOrigin
@RequestMapping("/driverinfo")
public class DriverInfoController {

    private DriverInfoService driverInfoService;

    private OrderService orderService;

    private DriverService driverService;

    private CargoService cargoService;

    @Autowired
    public DriverInfoController(DriverInfoService driverInfoService, OrderService orderService,
                                DriverService driverService, CargoService cargoService) {
        this.driverInfoService = driverInfoService;
        this.orderService = orderService;
        this.driverService = driverService;
        this.cargoService = cargoService;
    }

    @GetMapping("/{id}")
    public DriverInfoDto getDriverInfo(@PathVariable String id) {
        return driverInfoService.getDriverInfo(Integer.parseInt(id));
    }

    @DeleteMapping("/done/{id}")
    public void markOrderAsDone(@PathVariable String id) {
        orderService.markOrderAsDone(Integer.parseInt(id));
    }

    @PutMapping("/updateStatus/{id}")
    public void updateDriverStatus(@PathVariable String id,@RequestBody String status){
        driverService.updateStatus(id,status);
    }

    @PutMapping("/updateCargo/{id}")
    public void updateCargoStatus(@PathVariable String id, @RequestBody String cargoStatus){
        cargoService.updateStatus(id,cargoStatus);
    }
}
