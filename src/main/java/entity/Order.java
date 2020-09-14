package entity;

import enums.OperationType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;


import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    public Order() {
        this.waypoints = new LinkedHashSet<>();
        this.drivers = new HashSet<>();
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "isComplete")
    private boolean isComplete;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Waypoint> waypoints;

    @OneToOne
    @JoinColumn(name = "vehicle")
    private Vehicle vehicle;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cargo")
    private Cargo cargo;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private Set<Driver> drivers;


    public CompleteOrder convertIntoCompleteOrder() {
        CompleteOrder completeOrder = new CompleteOrder();
        completeOrder.setOrderId(String.valueOf(this.getId()));
        completeOrder.setCargoName(this.getCargo().getName());
        completeOrder.setCargoWeight(this.getCargo().getWeight());
        completeOrder.setVehicleName(this.getVehicle().getName());

        Waypoint[] waypoints = this.getWaypoints().toArray(new Waypoint[0]);
        if (waypoints[0].getOperationType().equals(OperationType.LOADING)) {
            completeOrder.setStartCity(waypoints[0].getCity().getName());
            completeOrder.setDestinationCity(waypoints[1].getCity().getName());
        } else {
            completeOrder.setStartCity(waypoints[1].getCity().getName());
            completeOrder.setDestinationCity(waypoints[0].getCity().getName());
        }
        return completeOrder;
    }
}

