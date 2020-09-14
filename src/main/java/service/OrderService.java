package service;

import dao.CompleteOrderDao;
import dao.OrderDao;
import dto.OrderDto;
import dtoconverter.Converter;
import dtoconverter.OrderConverter;
import entity.*;
import eventlistener.CustomPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.WorkingHoursCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {

    private OrderDao orderDao;

    private CityService cityService;

    private CompleteOrderDao completeOrderDao;

    private Converter<Order, OrderDto> converter;

    private CargoService cargoService;

    private DriverService driverService;

    private VehicleService vehicleService;

    private WaypointService waypointService;

    private WorkingHoursCalculator workingHoursCalculator;

    private CustomPublisher publisher;

    @Autowired
    public OrderService(OrderDao orderDao, OrderConverter converter, CargoService cargoService,
                        DriverService driverService, VehicleService vehicleService,
                        WaypointService waypointService, WorkingHoursCalculator workingHoursCalculator,
                        CompleteOrderDao completeOrderDao, CityService cityService, CustomPublisher publisher) {
        this.orderDao = orderDao;
        this.converter = converter;
        this.cargoService = cargoService;
        this.driverService = driverService;
        this.vehicleService = vehicleService;
        this.waypointService = waypointService;
        this.workingHoursCalculator = workingHoursCalculator;
        this.completeOrderDao = completeOrderDao;
        this.cityService=cityService;
        this.publisher=publisher;
    }

    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderDao.list();
        return orders.stream()
                .map(o -> converter.convertIntoDto(o))
                .collect(Collectors.toList());
    }

    public void configureAndAddOrder(OrderDto orderDto) {
        //create Cargo
        Order order = new Order();
        Cargo cargo = cargoService.createCargo(orderDto);
        order.setCargo(cargo);
        order.setComplete(false);

        //Find drivers
        Set<Driver> drivers = driverService.selectDrivers(orderDto);
        Vehicle vehicle = vehicleService.getVehicleForOrder(orderDto);
        drivers.forEach(o -> o.setVehicle(vehicle));
        vehicle.setCargo(cargo);
        vehicle.setDrivers(new ArrayList<>(drivers));

        //Create Waypoints
        Set<Waypoint> waypoints = waypointService.createWaypoints(orderDto);
        waypoints.forEach(o -> o.setOrder(order));

        //Update all entities and calc working hours
        order.setDrivers(drivers);
        order.setVehicle(vehicle);
        order.setWaypoints(waypoints);
        orderDao.add(order);
        drivers.forEach(o -> o.setWorkingHours(workingHoursCalculator.calculateWorkingHours(orderDto)));
        drivers.forEach(o -> o.setOrder(order));
        drivers.forEach(driverService::updateInDB);
        vehicleService.updateInDB(vehicle);
        publisher.publishCustomEvent();
    }

    public void markOrderAsDone(int id) {
        Order order = orderDao.getById(id);
        order.setComplete(true);

        CompleteOrder completeOrder = order.convertIntoCompleteOrder();
        completeOrderDao.add(completeOrder);

        //Unpin order and vehicle from drivers
        Set<Driver> drivers = order.getDrivers();
        drivers.forEach(o -> o.setOrder(null));
        drivers.forEach(o -> o.setVehicle(null));
        drivers.forEach(o->o.setCurrentCity(cityService.getByName(completeOrder.getDestinationCity())));
        drivers.forEach(driverService::updateInDB);

        //Unpin cargo from vehicle
        Vehicle vehicle = order.getVehicle();
        vehicle.setCargo(null);
        vehicle.setCurrentCity(cityService.getByName(completeOrder.getDestinationCity()));
        vehicleService.updateInDB(vehicle);

        order.setVehicle(null);
        order.setDrivers(null);

        orderDao.delete(order);
        publisher.publishCustomEvent();
    }




}
