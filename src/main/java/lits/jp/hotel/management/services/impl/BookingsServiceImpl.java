package lits.jp.hotel.management.services.impl;

import lits.jp.hotel.management.dtos.BookingsDTO;
import lits.jp.hotel.management.mapper.BookingsMapper;
import lits.jp.hotel.management.models.Bookings;
import lits.jp.hotel.management.repository.BookingsRepository;
import lits.jp.hotel.management.services.BookingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingsServiceImpl implements BookingsService {
    @Autowired
    BookingsRepository bookingsRepository;

    @Autowired
    BookingsMapper bookingsMapper;

    @Override
    public Bookings addBooking(Bookings bookings) {
        return bookingsRepository.save(bookings);
    }


    @Override
    public List<Bookings> showAllBookingsOnDate(LocalDate date) {
        return bookingsRepository.showAllBookingsOnDate(date);
    }


}
