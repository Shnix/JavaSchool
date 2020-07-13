package entity;

import enums.DriverStatus;
import enums.DriverType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    @NotNull
    private String firstName;

    @Column(name = "lastName")
    @NotNull
    private String lastName;

    @Column(name = "driverType")
    @Enumerated(EnumType.STRING)
    private DriverType driverType;

    @Column(name = "workingHours")
    @NotNull
    private int workingHours;

    @Column(name = "driverStatus")
    @Enumerated(EnumType.STRING)
    private DriverStatus status;

    @Column(name = "city")
    @NotNull
    private City currentCity;

    @Column(name = "vehicle")
    @NotNull
    private Vehicle vehicle;

    @Column(name = "order")
    @NotNull
    private Order orders;
}
