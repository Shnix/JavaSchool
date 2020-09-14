package dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
public class VehicleDto {

    private int id;

    @NotNull
    @Pattern(regexp = "[A-Z]{2}\\d{5}", message = "Vehicle name must be 2 letters 5 digits. Example:'AB12345'")
    private String name;

    private String vehicleType;

    private int driversCount;

    @NotNull
    @Min(value = 1, message = "Capacity must be >=1")
    @Max(value = 999999, message = "Max capacity is 999999")
    private int capacityInTons;

    private String cargo;

    private String condition;

    @NotNull
    @Pattern(regexp = "^[A-Za-z]+$", message = "Invalid name for city. ")
    private String currentCity;

    public VehicleDto(int id, String name, String vehicleType, int driversCount, int capacityInTons, String cargo, String condition, String currentCity) {
        this.id = id;
        this.name = name;
        this.vehicleType = vehicleType;
        this.driversCount = driversCount;
        this.capacityInTons = capacityInTons;
        this.cargo = cargo;
        this.condition = condition;
        this.currentCity = currentCity;
    }
}
