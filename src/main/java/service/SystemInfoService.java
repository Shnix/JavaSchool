package service;


import dto.SystemInfoDto;
import entity.Vehicle;
import enums.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SystemInfoService {

    private VehicleService vehicleService;

    private DriverService driverService;

    private CompleteOrderService orderService;

    @Autowired
    public SystemInfoService(VehicleService vehicleService, DriverService driverService, CompleteOrderService orderService) {
        this.vehicleService = vehicleService;
        this.driverService = driverService;
        this.orderService = orderService;
    }

    public SystemInfoDto getSystemStatistics(){
        return SystemInfoDto.builder()
                .availableBoatsCount(vehicleService.getAvailableVehiclesCount(VehicleType.BOAT))
                .unavailableBoatsCount(vehicleService.getUnavailableVehiclesCount(VehicleType.BOAT))
                .brokenBoatsCount(vehicleService.getBrokenVehiclesCount(VehicleType.BOAT))
                .availablePlainsCount(vehicleService.getAvailableVehiclesCount(VehicleType.PLAIN))
                .unavailablePlainsCount(vehicleService.getUnavailableVehiclesCount(VehicleType.PLAIN))
                .brokenPlainsCount(vehicleService.getBrokenVehiclesCount(VehicleType.PLAIN))
                .availableDriversCount(driverService.getAvailableDriversCount())
                .unavailableDriversCount(driverService.getUnavailableDriversCount())
                .orderDtoList(orderService.getLastTenOrders())
                .build();
    }
}
