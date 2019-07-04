package lits.jp.hotel.management.service;

import lits.jp.hotel.management.dtos.BookingsDTO;
import lits.jp.hotel.management.dtos.RoomsDTO;
import lits.jp.hotel.management.models.Rooms;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface RoomService {

    RoomsDTO addRoom(RoomsDTO roomsDTO);
    void deleteRoom(int number);
    List<RoomsDTO> showAllRooms();
    List<BookingsDTO> showBookingHistory(int number);

}
