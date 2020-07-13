package entity;


import enums.VehicleCondition;
import enums.VehicleType;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "vehicleCondition")
    @Enumerated(EnumType.STRING)
    private VehicleCondition condition;

    @Column(name = "city")
    @NotNull
    private City currentCity;
}
