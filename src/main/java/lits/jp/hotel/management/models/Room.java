package lits.jp.hotel.management.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int number;

    @Column(name = "type")
    public String type;

    @Column(name = "is_occupied")
    public boolean isOccupied;
}
