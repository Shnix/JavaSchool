package entity;


import enums.VehicleCondition;
import enums.VehicleType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "vehicles")
public class Vehicle {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "vehicleType")
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @Column(name = "driversCount")
    @NotNull
    private int driversCount;

    @Column(name = "capacityInTons")
    @NotNull
    private int capacityInTons;

    @OneToOne
    @JoinColumn(name = "cargo")
    private Cargo cargo;

    @Column(name = "vehicleCondition")
    @Enumerated(EnumType.STRING)
    private VehicleCondition condition;

    @OneToOne
    @JoinColumn(name = "city")
    private City currentCity;

    public Vehicle(String name, VehicleType vehicleType,  int driversCount,int capacityInTons, VehicleCondition condition, City currentCity) {
        this.name = name;
        this.vehicleType = vehicleType;
        this.driversCount = driversCount;
        this.capacityInTons = capacityInTons;
        this.condition = condition;
        this.currentCity = currentCity;
    }
}
