package lits.jp.hotel.management.models;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "rooms")
public class Rooms {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;

    @Column(name = "number")
    private int number;

    @Column(name = "type")
    private String type;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private List<Bookings> bookingHistory;
}
