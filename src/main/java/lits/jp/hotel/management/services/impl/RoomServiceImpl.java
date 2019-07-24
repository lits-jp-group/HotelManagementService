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
    public RoomsDTO addRoom(RoomsDTO roomsDTO) {
        Rooms rooms=roomsMapper.toEntity(roomsDTO);
        return roomsMapper.toDto(roomsRepository.save(rooms));
    }


    @Override
    public void deleteRoom(int number) {
        roomsRepository.deleteByNumber(number);
    }



    @Override
    public List<Rooms> showAvailableRoomsOnDate(LocalDate date) {
        List<Rooms> allRooms = new ArrayList<>();
        List<Rooms> bookedRooms = new ArrayList<>();
        List<Rooms> availableRooms = new ArrayList<>();
        List<Bookings> bookings= bookingsRepository.showAllBookingsOnDate(date);

        allRooms.addAll(roomsRepository.showAllRooms());

        for (Bookings b:bookings) {
            int roomId= b.getRoom().getRoomId();
            bookedRooms.add(roomsRepository.findByRoomId(roomId));
        }

        int allRoomId; //двійна вложеність перформанс буде хромати
        int bookedRoomId;
        for (Rooms rooms:allRooms){
            allRoomId=rooms.getRoomId();
            for (Rooms rooms1:bookedRooms) {
                bookedRoomId = rooms1.getRoomId();
                if(allRoomId!=bookedRoomId){
                    availableRooms.add(roomsRepository.findByRoomId(allRoomId));
                }
            }
        }
        return null;
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
        List<BookingsDTO>bookingsDTO = bookings.stream().map(bookings1 -> bookingsMapper.toDto(bookings1)).collect(Collectors.toList());
// convert to DTO better to do in controllers
        for (BookingsDTO b:bookingsDTO) {
            int roomId= b.getRoom().getRoomId();
            bookedRooms.add(roomsRepository.findByRoomId(roomId));
        }
        return null;
    }
}


