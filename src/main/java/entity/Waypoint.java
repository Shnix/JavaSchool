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

    @OneToOne
    @JoinColumn(name = "city")
    private City city;

    @OneToOne
    @JoinColumn(name = "cargo")
    private  Cargo cargo;

    @Column(name = "operationType")
    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    @ManyToOne
    @JoinColumn(name = "`order`")
    private Order order;

    public Waypoint(City city,Cargo cargo, OperationType operationType,Order order) {
        this.city = city;
        this.cargo = cargo;
        this.operationType = operationType;
        this.order = order;
    }
}
