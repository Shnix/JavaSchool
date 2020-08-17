package service;

import dao.CityDao;
import dao.DriverDao;
import dao.VehicleDao;
import dto.OrderDto;
import dto.VehicleDto;
import dtoconverter.Converter;
import dtoconverter.VehicleConverter;
import entity.Driver;
import entity.Vehicle;
import enums.VehicleCondition;
import exception.VehicleHaveCargoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class VehicleService {

    private VehicleDao vehicleDao;

    private CityDao cityDao;

    private DriverService driverService;

    private Converter<Vehicle, VehicleDto> converter;

    @Autowired
    public VehicleService(VehicleDao vehicleDao, VehicleConverter converter, CityDao cityDao, DriverService driverService) {
        this.vehicleDao = vehicleDao;
        this.converter = converter;
        this.cityDao = cityDao;
        this.driverService = driverService;
    }

    public List<VehicleDto> getAllVehicles() {
        List<Vehicle> vehicles = vehicleDao.list();
        return vehicles.stream().map(o -> converter.convertIntoDto(o))
                .collect(Collectors.toList());
    }

    public void deleteVehicle(String id) throws VehicleHaveCargoException {
        Vehicle vehicle = vehicleDao.getById(Integer.parseInt(id));
        if (vehicle.getCargo() != null) {
            throw new VehicleHaveCargoException("Vehicle have cargo. Can't be deleted.");
        }
        List<Driver> drivers = vehicle.getDrivers();
        driverService.removeVehicles(drivers);
        vehicleDao.delete(vehicle);
    }

    public void addVehicle(VehicleDto vehicleDto) {
        Vehicle vehicle = converter.convertFromDto(vehicleDto);
        vehicleDao.add(vehicle);
    }

    public void update(String id, VehicleDto vehicleDto) {
        Vehicle vehicle = vehicleDao.getById(Integer.parseInt(id));
        vehicle.setName(vehicleDto.getName());
        vehicle.setCapacityInTons(vehicleDto.getCapacityInTons());
        vehicle.setCurrentCity(cityDao.getByName(vehicleDto.getCurrentCity()));
        vehicle.setCondition(VehicleCondition.valueOf(vehicleDto.getCondition().toUpperCase()));
        vehicleDao.update(vehicle);
    }

    public void updateInDB(Vehicle vehicle) {
        vehicleDao.update(vehicle);
    }

    public Vehicle getVehicleForOrder(OrderDto orderDto) {
        Vehicle vehicle = vehicleDao.getVehicleForOrder(orderDto);
        if (vehicle.getName() == null) {
            throw new RuntimeException("Can't get Vehicle.");
        }
        return vehicle;
    }
}
