package lits.jp.hotel.management.services.impl;

import lits.jp.hotel.management.dtos.BookingsDTO;
import lits.jp.hotel.management.dtos.RoomsDTO;
import lits.jp.hotel.management.mapper.BookingsMapper;
import lits.jp.hotel.management.mapper.RoomsMapper;
import lits.jp.hotel.management.models.Bookings;
import lits.jp.hotel.management.models.Rooms;
import lits.jp.hotel.management.repository.BookingsRepository;
import lits.jp.hotel.management.repository.RoomsRepository;
import lits.jp.hotel.management.services.RoomService;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomsMapper roomsMapper;
    @Autowired
    RoomsRepository roomsRepository;
    @Autowired
    BookingsRepository bookingsRepository;
    @Autowired
    BookingsMapper bookingsMapper;

    @Override
    public Rooms addRoom(Rooms rooms) {
                return roomsRepository.save(rooms);
    }


    @Override
    public void deleteRoom(int number) {
        roomsRepository.deleteByNumber(number);
    }



    @Override
    public List<Rooms> showAvailableRoomsOnDate(LocalDate date) {
        List<Rooms> allRooms = showAllRooms();
        List<Rooms> bookedRooms = showBookedRoomsOnDate(date);
        List<Rooms> availableRooms = new ArrayList<>();

        availableRooms= allRooms.stream().filter(a->!bookedRooms.contains(a)).collect(Collectors.toList());

//        for (Rooms all:allRooms) {
//            for (Rooms booked:bookedRooms){
//                if(all!=booked){
//                    availableRooms.add(all);
//                }
//            }
//
//        }
        return availableRooms;
    }


    @Override
    public List<Rooms> showAllRooms(){
        List<Rooms> allRooms = new ArrayList<>();
        allRooms.addAll(roomsRepository.showAllRooms());

        return allRooms;
    }

    @Override
    public List<Bookings> showBookingHistory(int number) {
        return null;
    }

    public List<Rooms> showBookedRoomsOnDate (LocalDate date){
        List<Rooms> bookedRooms = new ArrayList<>();
        List<Bookings> bookings= bookingsRepository.showAllBookingsOnDate(date);
        bookings.forEach(b->bookedRooms.add(b.getRoom()));
               return bookedRooms;
    }
}


