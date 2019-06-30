package lits.jp.hotel.management.dtos;

import lits.jp.hotel.management.models.Bookings;
import lombok.Data;

import javax.persistence.Column;
import java.util.List;

@Data
public class GuestsDTO {

    private String firstName;

    private String lastName;

    private int age;

    private String email;

    private String telephone;

    private List<BookingsDTO> bookingsList;
}
