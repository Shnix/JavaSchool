package entity;

import enums.CargoStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "cargoes")
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

    @OneToOne(mappedBy = "cargo", fetch = FetchType.EAGER)
    private Vehicle vehicle;

    public Cargo(String name, int weight, CargoStatus cargoStatus) {
        this.name = name;
        this.weight = weight;
        this.cargoStatus = cargoStatus;
    }

}
