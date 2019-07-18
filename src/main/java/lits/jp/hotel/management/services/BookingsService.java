package lits.jp.hotel.management.services;

import lits.jp.hotel.management.dtos.BookingsDTO;
import lits.jp.hotel.management.repository.GuestsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public interface BookingsService {



    BookingsDTO addBooking (BookingsDTO booking);


    List<BookingsDTO> showAllBookingsOnDate(Date date);

}
