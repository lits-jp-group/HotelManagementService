package lits.jp.hotel.management.models;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "guests")
public class Guests {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int guestId;

  @Column(name = "first_name", nullable = false)
  @NotNull
  private String firstName;

  @Column(name = "last_name", nullable = false)
  @NotNull
  private String lastName;

  @Column(name = "age")
  private Integer age;

  @Column(name = "email")
  private String email;

  @Column(name = "telephone")
  private String telephone;

  @OneToMany(mappedBy = "guest", fetch = FetchType.LAZY)
  List<Bookings> bookingsList;
}
