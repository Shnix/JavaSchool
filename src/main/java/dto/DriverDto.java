package dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class DriverDto implements Dto {

    private int id;

    private String firstName;

    private String lastName;

    private String  driverType;

    private Integer workingHours;

    private String status;

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
