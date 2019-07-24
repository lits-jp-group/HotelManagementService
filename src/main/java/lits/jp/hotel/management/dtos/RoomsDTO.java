package lits.jp.hotel.management.dtos;

import java.util.List;
import lombok.Data;

@Data
public class RoomsDTO {

  private int number;

  private String type;

  private List<BookingsDTO> bookingHistory;
}
