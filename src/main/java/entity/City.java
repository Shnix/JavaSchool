package entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cities")
public class City {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "latitude")
    @NotNull
    private long latitude;

    @Column(name = "longitude")
    @NotNull
    private long longitude;

    @Column(name = "havePort")
    @NotNull
    private boolean havePort;

    public City(String name, long latitude, long longitude, boolean havePort) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.havePort = havePort;
    }
}
