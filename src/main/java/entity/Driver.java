package entity;

import dto.DriverDto;
import enums.DriverStatus;
import enums.DriverType;
import enums.VehicleType;
import exception.VehicleTypeException;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "driverType")
    @Enumerated(EnumType.STRING)
    private DriverType driverType;

    @Column(name = "workingHours")
    private int workingHours;

    @Column(name = "driverStatus")
    @Enumerated(EnumType.STRING)
    private DriverStatus status;

    @OneToOne
    @JoinColumn(name = "city")
    private City currentCity;

    @OneToOne
    @JoinColumn(name = "vehicle")
    private Vehicle vehicle;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "`order`")
    private Order order;

    public Driver(String firstName,String lastName, DriverType driverType,int workingHours, DriverStatus status,City currentCity) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.driverType = driverType;
        this.workingHours = workingHours;
        this.status = status;
        this.currentCity = currentCity;
    }

    public void setVehicle(Vehicle vehicle) throws VehicleTypeException {
        if(this.getDriverType()==DriverType.PILOT&&vehicle.getVehicleType()== VehicleType.BOAT){
            throw new VehicleTypeException();
        }
        if(this.getDriverType()==DriverType.SAILOR&&vehicle.getVehicleType()== VehicleType.PLAIN){
            throw new VehicleTypeException();
        }
        this.vehicle = vehicle;
    }
}
