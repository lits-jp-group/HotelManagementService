package lits.jp.hotel.management.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Entity
@Table(name = "rooms")
public class Rooms {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;

    @Column(name = "number")
    @NotEmpty(message = "Please provide a room number")
    private int number;

    @NotEmpty(message = "Please provide a room type")
    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "room")
    private List<Bookings> bookingHistory; // = newArrayList
}
