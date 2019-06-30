package lits.jp.hotel.management.dtos;

import lombok.Data;

import java.util.List;

@Data
public class RoomsDTO {

    private int number;

    private String type;

    private boolean isOccupied;

    private List<BookingsDTO> bookingHistory;
}
