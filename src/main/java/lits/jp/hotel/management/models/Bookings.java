package lits.jp.hotel.management.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "bookings")
public class Bookings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roomId")
    private Rooms room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guestId")
    private Guests guest;

    @Column(name = "date_in", nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateIn;

    @Column(name = "date_out", nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOut;

    @ManyToOne(fetch = FetchType.LAZY) // p.s. CascadeType added as repo test fails
    @JoinColumn(name = "staffId")
    private StaffMember staffHandling;
}
