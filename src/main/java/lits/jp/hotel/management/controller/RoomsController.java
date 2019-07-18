package lits.jp.hotel.management.controller;

import lits.jp.hotel.management.dtos.RoomsDTO;
import lits.jp.hotel.management.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/room") // rooms!
public class RoomsController {
    @Autowired
    RoomService roomService; // private

    @GetMapping("/all")// all remove
    List<RoomsDTO> showAllRooms(){
        return roomService.showAllRooms();
    }

    @PostMapping(value = "/addRoom") // remove addRoom
    public  RoomsDTO addRoom(RoomsDTO roomsDTO){ // add @RequestBody
        return roomService.addRoom(roomsDTO);
    }

    @GetMapping("/allbookedrooms")
    public List<RoomsDTO> showBookedRoomsOnDate(@RequestParam  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date){
       return roomService.showBookedRoomsOnDate(date);
    }

}