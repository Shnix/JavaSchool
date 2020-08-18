package dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Data
@NoArgsConstructor
public class DriverDto implements Dto {


    private int id;

    @NotNull
    @Pattern(regexp = "^[A-Za-z]+$", message = "First Name Invalid")
    private String firstName;

    @NotNull
    @Pattern(regexp = "^[A-Za-z]+$", message = "Last Name Invalid")
    private String lastName;

    private String driverType;

    @Min(value = 0, message = "Working hours cant be <0")
    @Max(value = 178, message = "Working hours cant be >178")
    private Integer workingHours;

    private String status;

    @NotNull
    private String currentCity;

    private String vehicle;

    private Integer order;

    public DriverDto(String firstName, String lastName, String driverType, String currentCity) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.driverType = driverType;
        this.currentCity = currentCity;
    }

    public DriverDto(String firstName, String lastName, String driverType, Integer workingHours, String status, String currentCity, String vehicle, Integer order) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.driverType = driverType;
        this.workingHours = workingHours;
        this.status = status;
        this.currentCity = currentCity;
        this.vehicle = vehicle;
        this.order = order;
    }
}
