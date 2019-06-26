package lits.jp.hotel.management.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@Entity
@Table(name = "bookings")
public class Bookings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;

    @OneToOne
    @JoinColumn(name="number")
    private Rooms room;

    @OneToOne
    @JoinColumn(name="guestId")
    private Guests guest;

    @Column(name = "date_in")
    @NotNull
    private Date dateIn;

    @Column(name = "date_out")
    @NotNull
    private Date dateOut;

}
