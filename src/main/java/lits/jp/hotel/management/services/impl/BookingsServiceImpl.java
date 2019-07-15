package lits.jp.hotel.management.services.impl;

import lits.jp.hotel.management.dtos.BookingsDTO;
import lits.jp.hotel.management.mapper.BookingsMapper;
import lits.jp.hotel.management.models.Bookings;
import lits.jp.hotel.management.repository.BookingsRepository;
import lits.jp.hotel.management.services.BookingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public BookingsDTO addBooking(BookingsDTO bookingsDTO) {
        Bookings bookings = bookingsMapper.toEntity(bookingsDTO);
        return bookingsMapper.toDto(bookingsRepository.save(bookings));
    }


    @Override
    public List<BookingsDTO> showAllBookingsOnDate(Date date) {
        List<Bookings> bookings= bookingsRepository.showAllBookingsOnDate(date);
        return bookings.stream().map(bookings1 -> bookingsMapper.toDto(bookings1)).collect(Collectors.toList());
    }


}
