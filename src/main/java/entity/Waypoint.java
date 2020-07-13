package entity;

import enums.OperationType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "waypoints")
public class Waypoint {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "city")
    @NotNull
    private City city;

    @Column(name = "cargo")
    @NotNull
    private  Cargo cargo;

    @Column(name = "operationType")
    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    @Column(name = "order")
    @NotNull
    private Order order;
}
