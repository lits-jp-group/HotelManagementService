package lits.jp.hotel.management.dtos;

import lombok.Data;

import javax.persistence.Column;

@Data
public class GuestsDTO {

    private String firstName;

    private String lastName;

    private int age;

    private String email;

    private String telephone;

}
