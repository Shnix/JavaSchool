package entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cities")
public class City  {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "latitude")
    private long latitude;

    @Column(name = "longitude")
    private long longitude;

    @Column(name = "havePort")
    private boolean havePort;

    public City(String name, long latitude, long longitude, boolean havePort) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.havePort = havePort;
    }

}
