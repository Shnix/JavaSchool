package dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VehicleDto {

    private int id;

    private String name;

    private String vehicleType;

    private int driversCount;

    private int capacityInTons;

    private String cargo;

    private String condition;

    private String currentCity;
}
