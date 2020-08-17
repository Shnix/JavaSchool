package dtoconverter;

import dto.CompleteOrderDto;
import entity.CompleteOrder;
import org.springframework.stereotype.Component;


@Component
public class CompleteOrderConverter implements Converter<CompleteOrder, CompleteOrderDto> {
    @Override
    public CompleteOrder convertFromDto(CompleteOrderDto completeOrderDto) {
        throw new RuntimeException("cant do dis");
    }

    @Override
    public CompleteOrderDto convertIntoDto(CompleteOrder completeOrder) {
        return CompleteOrderDto.builder()
                .orderId(completeOrder.getOrderId())
                .cargoName(completeOrder.getCargoName())
                .cargoWeight(completeOrder.getCargoWeight())
                .vehicleName(completeOrder.getVehicleName())
                .startCity(completeOrder.getStartCity())
                .destinationCity(completeOrder.getDestinationCity())
                .build();
    }
}
