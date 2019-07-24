package lits.jp.hotel.management.dtos;

import java.sql.Date;
import lits.jp.hotel.management.models.Guests;
import lits.jp.hotel.management.models.Rooms;
import lombok.Data;

@Data
public class BookingsDTO {

  private int bookingId;

  private Rooms room;

  private Guests guest;

  private Date dateIn; // if use date - better to use LocalDayTime, or LocalDate

  private Date dateOut;
}
