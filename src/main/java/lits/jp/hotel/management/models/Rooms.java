package lits.jp.hotel.management.models;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "rooms")
public class Rooms {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int roomId;

  @Column(name = "number")
  @NotNull(
      message =
          "Please provide a room number") // p.s. replaced for @NotNull - as repotest fails. for int
                                          // it is necessary to use NOTNULL!!!
  private int number;

  @Column(name = "type")
  @NotEmpty(message = "Please provide a room type")
  private String type;

  @OneToMany(
      mappedBy = "room",
      fetch = FetchType.LAZY,
      cascade = {CascadeType.ALL})
  private List<Bookings> bookingHistory;
}
