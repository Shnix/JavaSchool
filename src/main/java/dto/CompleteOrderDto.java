package dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class CompleteOrderDto {

    private String orderId;

    private String startCity;

    private String destinationCity;

    private String vehicleName;

    private String cargoName;

    private String cargoWeight;

    public CompleteOrderDto(String orderId, String startCity, String destinationCity, String vehicleName, String cargoName, String cargoWeight) {
        this.orderId = orderId;
        this.startCity = startCity;
        this.destinationCity = destinationCity;
        this.vehicleName = vehicleName;
        this.cargoName = cargoName;
        this.cargoWeight = cargoWeight;
    }
}
