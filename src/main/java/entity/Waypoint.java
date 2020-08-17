package entity;

import enums.OperationType;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;

@Getter
@Setter
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
    @Cascade({CascadeType.SAVE_UPDATE})
    private City city;

    @Column(name = "operationType")
    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    @ManyToOne
    @JoinColumn(name = "`order`")
    private Order order;

    public Waypoint(City city, OperationType operationType, Order order) {
        this.city = city;
        this.operationType = operationType;
        this.order = order;
    }
}
