package entity;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cities")
public class City {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "havePort")
    private boolean havePort;

    public City(String name, long latitude, long longitude, boolean havePort) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.havePort = havePort;
    }

}
