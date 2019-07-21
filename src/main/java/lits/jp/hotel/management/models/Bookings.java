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

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "roomId")
    private Rooms room;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "guestId")
    private Guests guest;

    @Column(name = "date_in", nullable = false)
    private Date dateIn;

    @Column(name = "date_out", nullable = false)
    private Date dateOut;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}) // p.s. CascadeType added as repo test fails
    @JoinColumn(name = "staffId")
    private StaffMember staffHandling;
}
