package dtoconverter;

import dto.DriverInfoDto;
import entity.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.WaypointService;

@Component
public class DriverInfoConverter implements Converter<Driver, DriverInfoDto> {

    private WaypointService waypointService;

    @Autowired
    public DriverInfoConverter(WaypointService waypointService) {
        this.waypointService = waypointService;
    }

    @Override
    public Driver convertFromDto(DriverInfoDto driverInfoDto) {
        throw new RuntimeException("cant use dis");
    }

    @Override
    public DriverInfoDto convertIntoDto(Driver driver) {
        if (driver.getOrder() != null) {
           return convertWithOrder(driver);
        }
        else {
            return DriverInfoDto.builder()
                    .id(driver.getId())
                    .firstName(driver.getFirstName())
                    .lastName(driver.getLastName())
                    .status(driver.getStatus().toString())
                    .build();
        }
    }

    private DriverInfoDto convertWithOrder(Driver driver) {
        return DriverInfoDto.builder()
                .id(driver.getId())
                .firstName(driver.getFirstName())
                .lastName(driver.getLastName())
                .cargoName(driver.getVehicle().getCargo().getName())
                .cargoWeight(String.valueOf(driver.getVehicle().getCargo().getWeight()))
                .cargoStatus(driver.getVehicle().getCargo().getCargoStatus().toString())
                .destinationCity(waypointService.getDestinationName(driver))
                .startCity(waypointService.getStartCityName(driver))
                .orderId(driver.getOrder().getId())
                .vehicleName(driver.getVehicle().getName())
                .status(driver.getStatus().toString())
                .build();
    }
}
