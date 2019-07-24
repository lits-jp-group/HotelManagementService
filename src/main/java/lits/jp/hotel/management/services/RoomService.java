package lits.jp.hotel.management.services;

import java.util.Date;
import java.util.List;
import lits.jp.hotel.management.dtos.BookingsDTO;
import lits.jp.hotel.management.dtos.RoomsDTO;
import org.springframework.stereotype.Service;

@Service // not necessary to use annotation here
public interface RoomService {

  RoomsDTO addRoom(RoomsDTO roomsDTO);

  void deleteRoom(int number);

  List<RoomsDTO> showAllRooms();

  List<BookingsDTO> showBookingHistory(int number);

  List<RoomsDTO> showAvailableRoomsOnDate(Date date);

  List<RoomsDTO> showBookedRoomsOnDate(Date date);
}
