package lits.jp.hotel.management.services;

import lits.jp.hotel.management.dtos.BookingsDTO;
import lits.jp.hotel.management.dtos.RoomsDTO;
import lits.jp.hotel.management.models.Rooms;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service // not necessary to use annotation here
public interface RoomService {

    RoomsDTO addRoom(RoomsDTO roomsDTO);
    void deleteRoom(int number);
    List<RoomsDTO> showAllRooms();
    List<BookingsDTO> showBookingHistory(int number);
    List<RoomsDTO> showAvailableRoomsOnDate(Date date);
    List<RoomsDTO> showBookedRoomsOnDate(Date date);


}
