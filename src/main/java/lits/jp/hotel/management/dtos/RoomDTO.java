package lits.jp.hotel.management.dtos;

import lombok.Data;

@Data
public class RoomDTO {

    public int number;

    public String type;

    public boolean isOccupied;
}
