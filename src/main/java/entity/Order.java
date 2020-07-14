package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order  {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "isComplete")
    @NotNull
    private boolean isComplete;

    @OneToMany
    private Set<Waypoint> waypoints;

    @OneToOne
    @JoinColumn(name = "vehicle")
    private Vehicle vehicle;

    @OneToMany
    private Set<Driver> drivers;

    public Order(boolean isComplete) {
        this.isComplete = isComplete;
        this.waypoints = new LinkedHashSet<>();
        this.drivers = new HashSet<>();
    }
}
