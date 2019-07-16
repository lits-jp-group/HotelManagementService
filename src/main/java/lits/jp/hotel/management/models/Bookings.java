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

    @ManyToOne // to do everything in LAZY method
    @JoinColumn(name = "roomId")
    private Rooms room;

    @ManyToOne
    @JoinColumn(name = "guestId")
    private Guests guest;

    @Column(name = "date_in")
    @NotNull
    private Date dateIn;

    @Column(name = "date_out") // nullable=true/false
    @NotNull // used for response and request body
    private Date dateOut;

    @ManyToOne
    @JoinColumn(name = "staffId")
    private StaffMember staffHandling;
}
