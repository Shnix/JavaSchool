package dtoconverter;

import dto.OrderDto;
import entity.Order;
import entity.Waypoint;
import enums.OperationType;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter implements Converter<Order, OrderDto> {

    @Override
    public Order convertFromDto(OrderDto orderDto)  {
        throw new RuntimeException("cant use dis");
    }

    public OrderDto convertIntoDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        Waypoint[] waypoints = order.getWaypoints().toArray(new Waypoint[0]);

        if (waypoints[0].getOperationType().equals(OperationType.LOADING)) {
            orderDto.setStart(waypoints[0].getCity().getName());
            orderDto.setDestination(waypoints[1].getCity().getName());
        } else {
            orderDto.setStart(waypoints[1].getCity().getName());
            orderDto.setDestination(waypoints[0].getCity().getName());
        }

        orderDto.setComplete(order.isComplete());
        orderDto.setVehicleName(order.getVehicle().getName());
        orderDto.setVehicleType(order.getVehicle().getVehicleType().toString().toLowerCase());
        orderDto.setCargoName(order.getCargo().getName());
        orderDto.setCargoStatus(order.getCargo().getCargoStatus().toString());
        orderDto.setCargoWeight(order.getCargo().getWeight());
        return orderDto;
    }
}
