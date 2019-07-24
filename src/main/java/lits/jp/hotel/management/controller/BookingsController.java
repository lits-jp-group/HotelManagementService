package lits.jp.hotel.management.controller;

import lits.jp.hotel.management.dtos.BookingsDTO;
import lits.jp.hotel.management.models.Bookings;
import lits.jp.hotel.management.services.BookingsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/bookings")
public class BookingsController {
    BookingsService bookingsService;

    ModelMapper mapper;
    @Autowired
    public BookingsController(BookingsService bookingsService, ModelMapper mapper) {
        this.bookingsService = bookingsService;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<BookingsDTO> newBooking(@RequestBody Bookings bookings){

        return new
                ResponseEntity<>(mapper.map(bookingsService.addBooking(bookings), BookingsDTO.class),
                HttpStatus.CREATED) ;
    }


    @GetMapping
    @ResponseBody
    public List<BookingsDTO> showAllBookingsOnDate(@RequestParam("date")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Bookings> bookings = bookingsService.showAllBookingsOnDate(date);
        List<BookingsDTO> bookingsDTO = new ArrayList<>();
        bookings.forEach(b->bookingsDTO.add(mapper.map(b,BookingsDTO.class)));
        return bookingsDTO;
    }

}
