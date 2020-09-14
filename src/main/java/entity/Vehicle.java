package entity;


import enums.VehicleCondition;
import enums.VehicleType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "vehicleType")
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @Column(name = "driversCount")
    private int driversCount;

    @Column(name = "capacityInTons")
    private int capacityInTons;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cargo")
    private Cargo cargo;

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.EAGER)
    private List<Driver> drivers;

    @Column(name = "vehicleCondition")
    @Enumerated(EnumType.STRING)
    private VehicleCondition condition;

    @Column(name = "deleted")
    private boolean deleted;

    @OneToOne
    @JoinColumn(name = "city")
    private City currentCity;

    public Vehicle(String name, VehicleType vehicleType, int driversCount, int capacityInTons, VehicleCondition condition, City currentCity) {
        this.name = name;
        this.vehicleType = vehicleType;
        this.driversCount = driversCount;
        this.capacityInTons = capacityInTons;
        this.condition = condition;
        this.currentCity = currentCity;
    }
}
