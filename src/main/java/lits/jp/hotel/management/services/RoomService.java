package lits.jp.hotel.management.services;

import lits.jp.hotel.management.dtos.BookingsDTO;
import lits.jp.hotel.management.dtos.RoomsDTO;
import lits.jp.hotel.management.models.Bookings;
import lits.jp.hotel.management.models.Rooms;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Service // not necessary to use annotation here
public interface RoomService {

    Rooms addRoom(Rooms rooms);
    void deleteRoom(int number);
    List<Rooms> showAllRooms();
    List<Bookings> showBookingHistory(int number);
    List<Rooms> showAvailableRoomsOnDate(LocalDate date);
    List<Rooms> showBookedRoomsOnDate(LocalDate date);


}
