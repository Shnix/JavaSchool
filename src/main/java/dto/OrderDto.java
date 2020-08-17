package dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class OrderDto {

    private int id;

    @NotNull
    @Pattern(regexp = "^[A-Za-z]+$", message = "Invalid name for current city. ")
    private String start;

    @NotNull
    @Pattern(regexp = "^[A-Za-z]+$", message = "Invalid name for destination city. ")
    private String destination;

    private boolean isComplete;

    private String vehicleName;

    @NotNull
    private String vehicleType;

    @NotNull
    @Pattern(regexp = "^[A-Za-z]+$", message = "Invalid name for cargo. ")
    private String cargoName;

    private String cargoStatus;

    @NotNull
    @Min(value = 1, message = "Cargo weight cant be <1. ")
    @Max(value = 999999, message = "Cargo weight cant be >999999. ")
    private int cargoWeight;
}
