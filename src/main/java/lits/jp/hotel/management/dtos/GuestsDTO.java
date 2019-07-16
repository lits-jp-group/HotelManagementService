package lits.jp.hotel.management.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Data
public class GuestsDTO {
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private Integer age;

    @Pattern(regexp = "^(.+)@(.+)$")
    private String email;

    private String telephone;

    private List<BookingsDTO> bookingsList;
}
