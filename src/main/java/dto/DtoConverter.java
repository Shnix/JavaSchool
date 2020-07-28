package dto;

import dao.CargoDao;
import dao.CityDao;
import entity.Driver;
import entity.Vehicle;
import enums.DriverStatus;
import enums.DriverType;
import enums.VehicleCondition;
import enums.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class DtoConverter {

    private CityDao cityDao;

    private CargoDao cargoDao;

    @Autowired
    public DtoConverter(CityDao cityDao, CargoDao cargoDao) {
        this.cityDao = cityDao;
    }

    public DriverDto convertIntoDto(Driver driver) {
        DriverDto driverDto = new DriverDto();
        driverDto.setId(driver.getId());
        driverDto.setFirstName(driver.getFirstName());
        driverDto.setLastName(driver.getLastName());
        driverDto.setCurrentCity(driver.getCurrentCity().getName());
        driverDto.setDriverType(driver.getDriverType().toString());
        if(driver.getOrder()!=null)
            driverDto.setOrder(driver.getOrder().getId());
        driverDto.setStatus(driver.getStatus().name());
        if(driver.getVehicle()!=null)
            driverDto.setVehicle(driver.getVehicle().getName());
        driverDto.setWorkingHours(driver.getWorkingHours());
        return driverDto;
    }

    @Transactional
    public Driver convertFromDto(DriverDto driverDto){
        Driver driver = new Driver();
        driver.setFirstName(driverDto.getFirstName());
        driver.setLastName(driverDto.getLastName());
        driver.setStatus(DriverStatus.REST);
        driver.setCurrentCity(cityDao.getByName(driverDto.getCurrentCity()));
        driver.setDriverType(DriverType.valueOf(driverDto.getDriverType().toUpperCase()));
        driver.setWorkingHours(0);
        return driver;
    }

    public VehicleDto convertIntoDto(Vehicle vehicle){
        VehicleDto vehicleDto = new VehicleDto();
        vehicleDto.setId(vehicle.getId());
        vehicleDto.setCapacityInTons(vehicle.getCapacityInTons());
        vehicleDto.setCondition(vehicle.getCondition().toString());
        vehicleDto.setCurrentCity(vehicle.getCurrentCity().getName());
        vehicleDto.setDriversCount(vehicle.getDriversCount());
        vehicleDto.setName(vehicle.getName());
        vehicleDto.setVehicleType(vehicle.getVehicleType().toString());
        vehicleDto.setCargo(vehicle.getCargo().getName());
        return vehicleDto;
    }

    public Vehicle convertFromDto(VehicleDto vehicleDto){
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleDto.getId());
        vehicle.setCapacityInTons(vehicleDto.getCapacityInTons());
        vehicle.setCondition(VehicleCondition.OK);
        vehicle.setCurrentCity(cityDao.getByName(vehicleDto.getCurrentCity()));
        vehicle.setDriversCount(vehicleDto.getDriversCount());
        vehicle.setName(vehicleDto.getName());
        vehicle.setVehicleType(VehicleType.valueOf(vehicleDto.getVehicleType()));
        vehicle.setCargo(cargoDao.getByName(vehicleDto.getCargo()));
        return vehicle;
    }

}
