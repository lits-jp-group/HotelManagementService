package lits.jp.hotel.management.models;

import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "bookings")
public class Bookings {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int bookingId;

  @ManyToOne(
      fetch = FetchType.LAZY,
      cascade = {CascadeType.ALL})
  @JoinColumn(name = "roomId")
  private Rooms room;

  @ManyToOne(
      fetch = FetchType.LAZY,
      cascade = {CascadeType.ALL})
  @JoinColumn(name = "guestId")
  private Guests guest;

  @Column(name = "date_in", nullable = false)
  private Date dateIn;

  @Column(name = "date_out", nullable = false)
  private Date dateOut;

  @ManyToOne(
      fetch = FetchType.LAZY,
      cascade = {CascadeType.ALL}) // p.s. CascadeType added as repo test fails
  @JoinColumn(name = "staffId")
  private StaffMember staffHandling;
}
