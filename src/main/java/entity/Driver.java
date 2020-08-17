package entity;

import enums.DriverStatus;
import enums.DriverType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
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

    @ManyToOne
    @JoinColumn(name = "vehicle")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "`order`")
    private Order order;

    public Driver(String firstName, String lastName, DriverType driverType, int workingHours, DriverStatus status, City currentCity) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.driverType = driverType;
        this.workingHours = workingHours;
        this.status = status;
        this.currentCity = currentCity;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
