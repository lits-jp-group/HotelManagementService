package lits.jp.hotel.management.dtos;

import lits.jp.hotel.management.models.Bookings;
import lombok.Data;

import java.util.List;

@Data
public class StaffMemberDTO {

    public String firstName;

    public String lastName;

    public List<BookingsDTO> bookingsHandled;

}
