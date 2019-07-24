package lits.jp.hotel.management.services;

import java.util.Date;
import java.util.List;
import lits.jp.hotel.management.dtos.BookingsDTO;

public interface BookingsService {

  BookingsDTO addBooking(BookingsDTO booking);

  List<BookingsDTO> showAllBookingsOnDate(Date date);
}
