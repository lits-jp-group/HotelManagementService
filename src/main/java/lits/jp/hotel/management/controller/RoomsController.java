package lits.jp.hotel.management.controller;

import lits.jp.hotel.management.dtos.RoomsDTO;
import lits.jp.hotel.management.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomsController {
    @Autowired
    RoomService roomService;

    @GetMapping("/allRooms")
    List<RoomsDTO> showAllRooms(){
        return roomService.showAllRooms();
    }

    @PostMapping(value = "/addRoom")
    public  RoomsDTO addRoom(RoomsDTO roomsDTO){
        return roomService.addRoom(roomsDTO);
    }

}
