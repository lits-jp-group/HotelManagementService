package lits.jp.hotel.management.dtos;


import lombok.Data;
import java.time.LocalDate;

@Data
public class BookingsDTO {

    private int bookingId;

    private Integer room_id;

    private Integer guest_id;

    private LocalDate dateIn;

    private LocalDate dateOut;

}
