package dtoconverter;

import dto.VehicleDto;
import entity.Vehicle;
import enums.VehicleCondition;
import enums.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.CargoService;
import service.CityService;

import javax.transaction.Transactional;

@Component
public class VehicleConverter implements Converter<Vehicle, VehicleDto> {

    private CityService cityService;

    private CargoService cargoService;

    @Autowired
    public VehicleConverter(CityService cityService, CargoService cargoService) {
        this.cargoService = cargoService;
        this.cityService = cityService;
    }

    public VehicleDto convertIntoDto(Vehicle vehicle) {
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setId(vehicle.getId());
        vehicleDto.setCapacityInTons(vehicle.getCapacityInTons());
        vehicleDto.setCondition(vehicle.getCondition().toString());
        vehicleDto.setCurrentCity(vehicle.getCurrentCity().getName());
        vehicleDto.setDriversCount(vehicle.getDriversCount());
        vehicleDto.setName(vehicle.getName());
        vehicleDto.setVehicleType(vehicle.getVehicleType().toString());
        if (vehicle.getCargo() != null) {
            vehicleDto.setCargo(vehicle.getCargo().getName());
        }
        return vehicleDto;
    }

    @Transactional
    public Vehicle convertFromDto(VehicleDto vehicleDto) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleDto.getId());
        vehicle.setCapacityInTons(vehicleDto.getCapacityInTons());
        vehicle.setCondition(VehicleCondition.OK);
        vehicle.setCurrentCity(cityService.getByName(vehicleDto.getCurrentCity()));
        if (vehicleDto.getVehicleType().equals("Boat")) {
            vehicle.setDriversCount(4);
        }
        if (vehicleDto.getVehicleType().equals("Plain")) {
            vehicle.setDriversCount(2);
        }
        vehicle.setName(vehicleDto.getName());
        vehicle.setVehicleType(VehicleType.valueOf(vehicleDto.getVehicleType().toUpperCase()));
        if (vehicleDto.getCargo() != null) {
            vehicle.setCargo(cargoService.getByName(vehicleDto.getCargo()));
        }
        return vehicle;
    }
}
