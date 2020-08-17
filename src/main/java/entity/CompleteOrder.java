package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "completeorders")
public class CompleteOrder {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "orderId")
    private String orderId;

    @Column(name = "startCity")
    private String startCity;

    @Column(name = "destinationCity")
    private String destinationCity;

    @Column(name = "vehicleName")
    private String vehicleName;

    @Column(name = "cargoName")
    private String cargoName;

    @Column(name = "cargoWeight")
    private String cargoWeight;

}
