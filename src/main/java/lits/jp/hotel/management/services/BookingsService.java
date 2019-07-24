package lits.jp.hotel.management.services;

import lits.jp.hotel.management.dtos.BookingsDTO;
import lits.jp.hotel.management.models.Bookings;
import lits.jp.hotel.management.repository.GuestsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface BookingsService {



    Bookings addBooking (Bookings booking);


    List<Bookings> showAllBookingsOnDate(LocalDate date);

}
