package service;

import dao.CityDao;
import dao.DriverDao;
import dto.DriverDto;
import dto.OrderDto;
import dtoconverter.Converter;
import dtoconverter.DriverConverter;
import entity.Driver;
import enums.DriverStatus;
import enums.VehicleType;
import exception.DriverExecutingOrderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DriverService {

    private DriverDao driverDao;

    private CityDao cityDao;

    private Converter<Driver, DriverDto> converter;

    @Autowired
    public DriverService(DriverDao driverDao, CityDao cityDao, DriverConverter converter) {
        this.cityDao = cityDao;
        this.driverDao = driverDao;
        this.converter = converter;
    }

    public List<DriverDto> getAllDrivers() {
        List<Driver> drivers = driverDao.list();
        return drivers.stream().map(o -> converter.convertIntoDto(o))
                .collect(Collectors.toList());
    }

    public void deleteDriver(String id) throws DriverExecutingOrderException {
        Driver driver = driverDao.getById(Integer.parseInt(id));
        if (driver.getOrder() != null && !driver.getOrder().isComplete()) {
            throw new DriverExecutingOrderException("Driver executing order. Can't be deleted.");
        }
        driverDao.delete(driver);
    }

    public void addDriver(DriverDto driver) {
        Driver driver1 = converter.convertFromDto(driver);
        driverDao.add(driver1);
    }

    public void update(String id, DriverDto driverDto) {
        Driver driver = driverDao.getById(Integer.parseInt(id));
        driver.setFirstName(driverDto.getFirstName());
        driver.setLastName(driverDto.getLastName());
        driver.setCurrentCity(cityDao.getByName(driverDto.getCurrentCity()));
        driverDao.update(driver);
    }

    public void updateStatus(String id, String status){
        Driver driver = driverDao.getById(Integer.parseInt(id));
        String trim = status.replaceAll("\"","");
        driver.setStatus(DriverStatus.valueOf(trim.toUpperCase()));
        driverDao.update(driver);
    }

    public void updateInDB(Driver driver) {
        driverDao.update(driver);
    }

    public void removeVehicles(List<Driver> drivers) {
        drivers.forEach(o -> o.setVehicle(null));
        drivers.forEach(driverDao::update);
    }

    public HashSet selectDrivers(OrderDto orderDto) {
        List drivers = driverDao.selectDrivers(orderDto);
        VehicleType vehicleType = VehicleType.valueOf(orderDto.getVehicleType().toUpperCase());

        if (!checkDrivers(drivers, vehicleType)) {
            throw new RuntimeException("Unable to select drivers");
        }

        if (vehicleType.equals(VehicleType.BOAT)) {
            return new HashSet<>(drivers.subList(0, 4));
        } else {
            return new HashSet<>(drivers.subList(0, 2));
        }
    }

    private boolean checkDrivers(List<Driver> drivers, VehicleType vehicleType) {
        if (vehicleType.equals(VehicleType.BOAT) && drivers.size() < 4) {
            return false;
        }
        if (vehicleType.equals(VehicleType.PLAIN) && drivers.size() < 2) {
            return false;
        }
        return true;
    }


}
