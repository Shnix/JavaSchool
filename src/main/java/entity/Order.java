package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "isComplete")
    private boolean isComplete;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<Waypoint> waypoints;

    @OneToOne
    @JoinColumn(name = "vehicle")
    private Vehicle vehicle;

    @OneToMany(mappedBy = "order")
    private Set<Driver> drivers;

    public Order(boolean isComplete) {
        this.isComplete = isComplete;
        this.waypoints = new LinkedHashSet<>();
        this.drivers = new HashSet<>();
    }
}

