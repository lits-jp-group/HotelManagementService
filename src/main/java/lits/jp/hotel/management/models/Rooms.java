package lits.jp.hotel.management.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "rooms")
public class Rooms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int number;

    @Column(name = "type")
    private String type;

    @Column(name = "is_occupied")
    private boolean isOccupied;
}
