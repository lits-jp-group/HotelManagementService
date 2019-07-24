package lits.jp.hotel.management.dtos;

import java.util.List;
import lombok.Data;

@Data
public class StaffMemberDTO {

  public String firstName;

  public String lastName;

  public List<BookingsDTO> bookingsHandled;
}
