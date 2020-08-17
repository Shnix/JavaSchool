package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class DriverInfoDto {

    private int id;

    private String firstName;

    private String lastName;

    private String vehicleName;

    private Integer orderId;

    private String startCity;

    private String destinationCity;

    private String status;

    private String cargoName;

    private String cargoWeight;

    private String cargoStatus;
}
