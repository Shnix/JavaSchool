package dtoconverter;

import dao.CargoDao;
import dao.CityDao;
import dto.DriverDto;
import entity.Driver;
import enums.DriverStatus;
import enums.DriverType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import service.CityService;


@Component
public class DriverConverter implements Converter<Driver, DriverDto> {

    private CityService cityService;

    @Autowired
    public DriverConverter(CityService cityService, CargoDao cargoDao) {
        this.cityService = cityService;
    }

    public DriverDto convertIntoDto(Driver driver) {
        DriverDto driverDto = new DriverDto();
        driverDto.setId(driver.getId());
        driverDto.setFirstName(driver.getFirstName());
        driverDto.setLastName(driver.getLastName());
        driverDto.setCurrentCity(driver.getCurrentCity().getName());
        driverDto.setDriverType(driver.getDriverType().toString());
        if (driver.getOrder() != null) {
            driverDto.setOrder(driver.getOrder().getId());
        }
        driverDto.setStatus(driver.getStatus().name());
        if (driver.getVehicle() != null) {
            driverDto.setVehicle(driver.getVehicle().getName());
        }
        driverDto.setWorkingHours(driver.getWorkingHours());
        return driverDto;
    }

    public Driver convertFromDto(DriverDto driverDto) {
        Driver driver = new Driver();
        driver.setFirstName(driverDto.getFirstName());
        driver.setLastName(driverDto.getLastName());
        driver.setStatus(DriverStatus.REST);
        driver.setCurrentCity(cityService.getByName(driverDto.getCurrentCity()));
        driver.setDriverType(DriverType.valueOf(driverDto.getDriverType().toUpperCase()));
        driver.setWorkingHours(0);
        return driver;
    }
}
