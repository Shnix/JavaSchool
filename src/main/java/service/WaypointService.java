package service;

import dto.OrderDto;
import entity.City;
import entity.Driver;
import entity.Order;
import entity.Waypoint;
import enums.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class WaypointService {

    private CityService cityService;

    @Autowired
    public WaypointService(CityService cityService) {
        this.cityService = cityService;
    }

    public Set<Waypoint> createWaypoints(OrderDto orderDto) {
        Set<Waypoint> waypoints = new LinkedHashSet<>();
        Waypoint start = new Waypoint();
        start.setOperationType(OperationType.LOADING);
        City startCity = cityService.getByName(orderDto.getStart());
        start.setCity(startCity);
        Waypoint destination = new Waypoint();
        destination.setOperationType(OperationType.UNLOADING);
        City destinationCity = cityService.getByName(orderDto.getDestination());
        destination.setCity(destinationCity);
        waypoints.add(start);
        waypoints.add(destination);
        return waypoints;
    }

    public String getStartCityName(Driver driver){
        Order order = driver.getOrder();

        return order.getWaypoints().stream()
                .filter(waypoint->waypoint.getOperationType().equals(OperationType.LOADING))
                .findFirst().get().getCity().getName();
    }

    public String getDestinationName(Driver driver){
        Order order = driver.getOrder();

        return order.getWaypoints().stream()
                .filter(waypoint->waypoint.getOperationType().equals(OperationType.UNLOADING))
                .findFirst().get().getCity().getName();
    }
}
