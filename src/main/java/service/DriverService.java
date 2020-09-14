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
import eventlistener.CustomPublisher;
import eventlistener.UpdateEvent;
import exception.DriverExecutingOrderException;
import exception.ServiceLayerException;
import messaging.MessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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

    private CustomPublisher publisher;

    @Autowired
    public DriverService(DriverDao driverDao, CityDao cityDao, DriverConverter converter,
                        CustomPublisher publisher ) {
        this.cityDao = cityDao;
        this.driverDao = driverDao;
        this.converter = converter;
        this.publisher=publisher;
    }

    public List<DriverDto> getAllDrivers() {
        List<Driver> drivers = driverDao.list();
        return drivers.stream()
                .filter(driver -> !driver.isDeleted())
                .map(driver -> converter.convertIntoDto(driver))
                .collect(Collectors.toList());
    }

    public void deleteDriver(String id) throws DriverExecutingOrderException {
        Driver driver = driverDao.getById(Integer.parseInt(id));
        if (driver.getOrder() != null && !driver.getOrder().isComplete()) {
            throw new DriverExecutingOrderException("Driver executing order. Can't be deleted.");
        }
        publisher.publishCustomEvent();
        driver.setDeleted(true);
        driverDao.update(driver);
    }

    public void addDriver(DriverDto driver) {
        Driver driver1 = converter.convertFromDto(driver);
        driverDao.add(driver1);
        publisher.publishCustomEvent();
    }

    public void updateDriver(String id, DriverDto driverDto) {
        Driver driver = driverDao.getById(Integer.parseInt(id));
        driver.setFirstName(driverDto.getFirstName());
        driver.setLastName(driverDto.getLastName());
        driver.setCurrentCity(cityDao.getByName(driverDto.getCurrentCity()));
        driver.setWorkingHours(driverDto.getWorkingHours());
        driverDao.update(driver);
        publisher.publishCustomEvent();
    }

    public void updateDriverStatus(String id, String status){
        Driver driver = driverDao.getById(Integer.parseInt(id));
        String trim = status.replaceAll("\"","");
        driver.setStatus(DriverStatus.valueOf(trim.toUpperCase()));
        driverDao.update(driver);
        publisher.publishCustomEvent();
    }

    public void updateInDB(Driver driver) {
        driverDao.update(driver);
    }

    public void removeVehicles(List<Driver> drivers) {
        drivers.forEach(driver -> driver.setVehicle(null));
        drivers.forEach(driverDao::update);
        publisher.publishCustomEvent();
    }

    public HashSet selectDrivers(OrderDto orderDto) {
        List drivers = driverDao.selectDrivers(orderDto);
        VehicleType vehicleType = VehicleType.valueOf(orderDto.getVehicleType().toUpperCase());

        if (!checkDrivers(drivers, vehicleType)) {
            throw new ServiceLayerException("Unable to select drivers");
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

    public Integer getAvailableDriversCount(){
        return Math.toIntExact(driverDao.list().stream()
                .filter(driver -> !driver.isDeleted())
                .filter(driver -> driver.getStatus().equals(DriverStatus.REST))
                .filter(driver -> driver.getOrder()==null)
                .count());
    }

    public Integer getUnavailableDriversCount(){
        return Math.toIntExact(driverDao.list().stream()
                .filter(driver -> !driver.isDeleted())
                .filter(driver -> driver.getOrder()!=null)
                .count());
    }


}
