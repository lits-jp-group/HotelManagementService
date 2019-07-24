package lits.jp.hotel.management.mapper;

import lits.jp.hotel.management.dtos.BookingsDTO;
import lits.jp.hotel.management.models.Bookings;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingsMapper {

  @Autowired private ModelMapper mapper;

  public Bookings toEntity(BookingsDTO bookingsDTO) {
    return mapper.map(bookingsDTO, Bookings.class);
  }

  public BookingsDTO toDto(Bookings bookings) {
    return mapper.map(bookings, BookingsDTO.class);
  }
}
