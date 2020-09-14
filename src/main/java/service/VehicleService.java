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
import enums.VehicleType;
import eventlistener.CustomPublisher;
import exception.ServiceLayerException;
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

    private CustomPublisher publisher;

    @Autowired
    public VehicleService(VehicleDao vehicleDao, VehicleConverter converter, CityDao cityDao,
                          DriverService driverService, CustomPublisher publisher) {
        this.vehicleDao = vehicleDao;
        this.converter = converter;
        this.cityDao = cityDao;
        this.driverService = driverService;
        this.publisher=publisher;
    }

    public List<VehicleDto> getAllVehicles() {
        List<Vehicle> vehicles = vehicleDao.list();
        return vehicles.stream()
                .filter(vehicle -> !vehicle.isDeleted())
                .map(o -> converter.convertIntoDto(o))
                .collect(Collectors.toList());
    }

    public void deleteVehicle(String id) throws VehicleHaveCargoException {
        Vehicle vehicle = vehicleDao.getById(Integer.parseInt(id));
        if (vehicle.getCargo() != null) {
            throw new VehicleHaveCargoException("Vehicle have cargo. Can't be deleted.");
        }
        List<Driver> drivers = vehicle.getDrivers();
        driverService.removeVehicles(drivers);
        vehicle.setDeleted(true);
        vehicleDao.update(vehicle);
        publisher.publishCustomEvent();
    }

    public void addVehicle(VehicleDto vehicleDto) {
        Vehicle vehicle = converter.convertFromDto(vehicleDto);
        vehicleDao.add(vehicle);
        publisher.publishCustomEvent();
    }

    public void update(String id, VehicleDto vehicleDto) {
        Vehicle vehicle = vehicleDao.getById(Integer.parseInt(id));
        vehicle.setName(vehicleDto.getName());
        vehicle.setCapacityInTons(vehicleDto.getCapacityInTons());
        vehicle.setCurrentCity(cityDao.getByName(vehicleDto.getCurrentCity()));
        vehicle.setCondition(VehicleCondition.valueOf(vehicleDto.getCondition().toUpperCase()));
        vehicleDao.update(vehicle);
        publisher.publishCustomEvent();
    }

    public void updateInDB(Vehicle vehicle) {
        vehicleDao.update(vehicle);
    }

    public Vehicle getVehicleForOrder(OrderDto orderDto) {
        Vehicle vehicle = vehicleDao.getVehicleForOrder(orderDto);
        if (vehicle.getName() == null) {
            throw new ServiceLayerException("No Vehicle for this order.");
        }
        return vehicle;
    }

    public Integer getAvailableVehiclesCount(VehicleType vehicleType){
        return vehicleDao.list().stream()
                .filter(vehicle -> !vehicle.isDeleted())
                .filter(vehicle -> vehicle.getVehicleType().equals(vehicleType))
                .filter(vehicle -> vehicle.getCondition().equals(VehicleCondition.OK))
                .filter(vehicle -> vehicle.getCargo()==null)
                .collect(Collectors.toList())
                .size();
    }

    public Integer getUnavailableVehiclesCount(VehicleType vehicleType){
        return vehicleDao.list().stream()
                .filter(vehicle -> !vehicle.isDeleted())
                .filter(vehicle -> vehicle.getVehicleType().equals(vehicleType))
                .filter(vehicle -> vehicle.getCondition().equals(VehicleCondition.OK))
                .filter(vehicle -> vehicle.getCargo()!=null)
                .collect(Collectors.toList())
                .size();
    }

    public Integer getBrokenVehiclesCount(VehicleType vehicleType){
        return vehicleDao.list().stream()
                .filter(vehicle -> !vehicle.isDeleted())
                .filter(vehicle -> vehicle.getVehicleType().equals(vehicleType))
                .filter(vehicle -> vehicle.getCondition().equals(VehicleCondition.BROKEN))
                .collect(Collectors.toList())
                .size();
    }




}
