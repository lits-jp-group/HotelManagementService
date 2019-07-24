package lits.jp.hotel.management.dtos;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
public class GuestsDTO {
  @NotNull private String firstName;

  @NotNull private String lastName;

  private Integer age;

  @Pattern(regexp = "^(.+)@(.+)$")
  private String email;

  private String telephone;

  private List<BookingsDTO> bookingsList;
}
