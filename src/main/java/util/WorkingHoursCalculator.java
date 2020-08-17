package util;

import dto.OrderDto;
import entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.CityService;

@Component
public class WorkingHoursCalculator {

    private static final int PLAIN_AVERAGE_SPEED = 850;

    private static final int PREPARATION_TIME = 3;

    private static final int BOAT_AVERAGE_SPEED = 35;

    private static final int MAX_WORKING_HOURS = 178;

    private static String TIME_EXCEPTION_MESSAGE =
            "Can't find drivers because its take too much time to complete the order.";

    private CityService cityService;


    @Autowired
    public WorkingHoursCalculator(CityService cityService) {
        this.cityService = cityService;
    }

    public int calculateWorkingHours(OrderDto orderDto) {
        City start = cityService.getByName(orderDto.getStart());
        City destination = cityService.getByName(orderDto.getDestination());
        if (orderDto.getVehicleType().equals("Plain")) {
            return calculateForPlain(start, destination);
        } else {
            return calculateForBoat(start, destination);
        }
    }

    private static int calculateForBoat(City start, City destination) {
        double distance = DistanceCalculator.calculateDistance(start, destination);
        int workingHours = (int) (distance / BOAT_AVERAGE_SPEED);
        if (workingHours > MAX_WORKING_HOURS) {
            throw new RuntimeException(TIME_EXCEPTION_MESSAGE);
        }
        return workingHours + PREPARATION_TIME;
    }

    private static int calculateForPlain(City start, City destination) {
        double distance = DistanceCalculator.calculateDistance(start, destination);
        int workingHours = (int) (distance / PLAIN_AVERAGE_SPEED);
        if (workingHours > MAX_WORKING_HOURS) {
            throw new RuntimeException(TIME_EXCEPTION_MESSAGE);
        }
        return workingHours + PREPARATION_TIME;
    }
}
