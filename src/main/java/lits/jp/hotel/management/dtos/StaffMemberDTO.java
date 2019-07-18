package lits.jp.hotel.management.dtos;

import lombok.Data;

import java.util.List;

@Data
public class StaffMemberDTO {

    public String firstName;

    public String lastName;

    public List<BookingsDTO> bookingsHandled;

}
