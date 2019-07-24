package lits.jp.hotel.management.controller;

import java.util.Date;
import java.util.List;
import lits.jp.hotel.management.dtos.BookingsDTO;
import lits.jp.hotel.management.services.BookingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/bookings")
public class BookingsController {

  @Autowired BookingsService bookingsService;

  @PostMapping(value = "/booking") // remove /booking
  @ResponseBody
  public BookingsDTO newBooking(@RequestBody BookingsDTO bookingsDTO) {

    //        if(bookingsService.addBooking(bookingsDTO)!=null){
    //            return bookingsService.addBooking(bookingsDTO);}
    //        else
    return bookingsService.addBooking(bookingsDTO);
  }

  @GetMapping(value = "/date")
  @ResponseBody
  public List<BookingsDTO> showAllBookingsOnDate(
      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
    return bookingsService.showAllBookingsOnDate(date);
  }
}
