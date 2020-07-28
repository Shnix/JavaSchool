package entity;

import enums.CargoStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cargoes")
@Data
@NoArgsConstructor
public class Cargo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    private int weight;

    @Column(name = "cargoStatus")
    @Enumerated(EnumType.STRING)
    private CargoStatus cargoStatus;

    public Cargo(String name, int weight, CargoStatus cargoStatus) {
        this.name = name;
        this.weight = weight;
        this.cargoStatus = cargoStatus;
    }

}
