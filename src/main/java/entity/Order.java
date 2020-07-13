package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Set;
import java.util.SortedSet;

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
    @JoinTable(name = "waypoints")
    private SortedSet<Waypoint> waypoints;

    @Column(name = "vehicle")
    @NotNull
    private Vehicle vehicle;

    @OneToMany
    @JoinTable(name = "drivers")
    private Set<Driver> drivers;

}
